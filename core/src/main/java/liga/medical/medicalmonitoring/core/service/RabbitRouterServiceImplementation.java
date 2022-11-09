package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.dto.MessageType;
import liga.medical.dto.QueueNames;
import liga.medical.dto.RabbitMessageDto;
import liga.medical.medicalmonitoring.core.annotations.DbLog;
import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.api.RabbitSenderService;
import liga.medical.medicalmonitoring.core.configuration.ExchangeConfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitRouterServiceImplementation implements RabbitRouterService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final RabbitSenderService rabbitSenderService;

    public RabbitRouterServiceImplementation(ObjectMapper objectMapper,
                                             RabbitSenderService rabbitSenderService,
                                             RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitSenderService = rabbitSenderService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @DbLog
    @Override
    public void routeMessage(String message) {
        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
            MessageType messageType = rabbitMessageDto.getMessageType();

            switch (messageType) {
                case DAILY:
                    rabbitSenderService.sendMessage(rabbitMessageDto, QueueNames.DAILY_QUEUE_NAME);
                    break;
                case ALERT:
                    rabbitSenderService.sendMessage(rabbitMessageDto, QueueNames.ALERT_QUEUE_NAME);
                    break;
                default:
                    System.out.println("No information");
            }
        } catch (Exception ex) {
            rabbitSenderService.sendError(ex.getMessage());
        }
    }

    @DbLog
    @Override
    public void routeMessageWithCustomExchange(String message) {
        rabbitTemplate.setExchange(ExchangeConfiguration.DIRECT_EXCHANGE_NAME);

        try {
            RabbitMessageDto rabbitMessageDto = objectMapper.readValue(message, RabbitMessageDto.class);
            rabbitTemplate.convertAndSend(rabbitMessageDto.getMessageType().toString(), message);
            log.info("Роутер перенаправил сообщение при помощи кастомного обменника.");
        } catch (Exception ex) {
            rabbitTemplate.convertAndSend(MessageType.ERROR.toString(), ex.getMessage());
            log.info("При перенаправлении сообщения произошла ошибка.");
        }
    }
}

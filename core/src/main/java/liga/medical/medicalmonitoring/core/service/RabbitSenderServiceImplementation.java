package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.dto.QueueNames;
import liga.medical.dto.RabbitMessageDto;
import liga.medical.medicalmonitoring.core.annotations.DbLog;
import liga.medical.medicalmonitoring.core.api.RabbitSenderService;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitSenderServiceImplementation implements RabbitSenderService {

    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public RabbitSenderServiceImplementation(AmqpTemplate amqpTemplate, ObjectMapper objectMapper) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = objectMapper;
    }

    @DbLog
    @Override
    public void sendMessage(RabbitMessageDto messageDto, String queue) throws JsonProcessingException {
        String messageStr = objectMapper.writeValueAsString(messageDto);
        amqpTemplate.convertAndSend(queue, messageStr);
    }

    @DbLog
    @Override
    public void sendError(String message) {
        amqpTemplate.convertAndSend(QueueNames.ERROR_QUEUE_NAME, message);
    }
}

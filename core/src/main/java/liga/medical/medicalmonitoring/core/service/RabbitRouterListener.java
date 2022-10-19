package liga.medical.medicalmonitoring.core.service;

import liga.medical.medicalmonitoring.core.api.RabbitRouterService;
import liga.medical.medicalmonitoring.core.model.QueueNames;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitRouterListener {
    private final RabbitRouterService rabbitRouterService;

    public RabbitRouterListener(RabbitRouterService rabbitRouterService) {
        this.rabbitRouterService = rabbitRouterService;
    }

    @RabbitListener(queues = QueueNames.ROUTER_QUEUE_NAME)
    public void receiveAndRedirectMessage(String message) {
        rabbitRouterService.routeMessage(message);
    }
}

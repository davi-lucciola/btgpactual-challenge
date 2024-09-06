package io.api.btgpactual.queues.consumers;

import io.api.btgpactual.core.dto.CreateOrderDTO;
import io.api.btgpactual.config.QueueConfig;
import io.api.btgpactual.core.exceptions.DomainException;
import io.api.btgpactual.core.exceptions.ValidationException;
import io.api.btgpactual.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = QueueConfig.ORDER_CREATED_QUEUE)
    public void orderCreatedListener(Message<CreateOrderDTO> message) {
        try {
            CreateOrderDTO createOrderDTO = message.getPayload();
            logger.info("Pedido recebido: {}", createOrderDTO);

            orderService.createNewOrder(createOrderDTO);
        } catch (DomainException | ValidationException exception) {
            logger.error(exception.getMessage());
        } catch (Exception exception) {
            logger.error("Houve um erro inesperado ao processar pedido: {}", exception);
        }
    }
}

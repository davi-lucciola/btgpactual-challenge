package io.api.btgpactual.infra.queues.consumers;

import io.api.btgpactual.infra.annotations.Consumer;
import io.api.btgpactual.domain.dto.CreateOrderDTO;
import io.api.btgpactual.infra.queues.QueueConfig;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.domain.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;

@Consumer
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

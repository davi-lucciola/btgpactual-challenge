package io.api.btgpactual.usecases.command;

import io.api.btgpactual.core.usecases.commands.ProcessOrder;
import io.api.btgpactual.domain.dto.command.CreateOrderDTO;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.entities.Order;
import io.api.btgpactual.domain.entities.OrderItem;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.infra.repositories.commands.CustomerRepository;
import io.api.btgpactual.infra.repositories.commands.OrderItemRepository;
import io.api.btgpactual.infra.repositories.commands.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.api.btgpactual.mocks.OrderItemsMock.orderItemsEntities;
import static io.api.btgpactual.mocks.OrderMock.CreateOrderDTOMock;
import static io.api.btgpactual.mocks.OrderMock.OrderEntityMock;

@ExtendWith(MockitoExtension.class)
public class ProcessOrderTest {
    @InjectMocks
    private ProcessOrder processOrderUseCase;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Test
    public void createNewOrderCustomerNotFound() {
        CreateOrderDTO createOrderDTO = CreateOrderDTOMock();

        Mockito.when(customerRepository.findById(createOrderDTO.customerId())).thenReturn(Optional.ofNullable(null));

        DomainException exception = Assertions.assertThrows(DomainException.class, () -> {
            processOrderUseCase.processOrder(createOrderDTO);
        });

        Assertions.assertEquals("Cliente não encontrado.", exception.getMessage());
    }

    @Test
    public void createNewOrderSuccessfully() throws DomainException, ValidationException {
        CreateOrderDTO createOrderDTO = CreateOrderDTOMock();
        Order orderSaved = OrderEntityMock();
        List<OrderItem> orderItemsSaved = orderItemsEntities();

        Mockito.when(customerRepository.findById(createOrderDTO.customerId())).thenReturn(Optional.of(new Customer()));
        Mockito.when(orderRepository.findById(createOrderDTO.orderId())).thenReturn(Optional.ofNullable(null));
        Mockito.when(orderRepository.saveAndFlush(Mockito.any(Order.class))).thenReturn(orderSaved);
        Mockito.when(orderItemRepository.saveAllAndFlush(Mockito.any(List.class))).thenReturn(orderItemsSaved);

        Order order = processOrderUseCase.processOrder(createOrderDTO);

        Assertions.assertEquals(order.getId(), createOrderDTO.orderId());
        Assertions.assertEquals(order.getCustomer().getId(), createOrderDTO.customerId());
        Assertions.assertEquals(order.getTotal(), createOrderDTO.items().stream()
                .map(dto -> dto.price().multiply(BigDecimal.valueOf(dto.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        Assertions.assertTrue(order.getItems().size() == createOrderDTO.items().size());

        Assertions.assertTrue(order.getItems().stream().map(OrderItem::getId)
                .allMatch(orderItemId -> orderItemsSaved.stream().map(OrderItem::getId)
                        .anyMatch(orderItemSavedId -> Objects.equals(orderItemId, orderItemSavedId))));
    }
}

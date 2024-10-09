package io.api.btgpactual.usecases.queries;

import io.api.btgpactual.core.usecases.queries.GetCustomerOrders;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.exceptions.NoContentException;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.infra.repositories.commands.CustomerRepository;
import io.api.btgpactual.infra.repositories.queries.OrderQueryRepository;
import io.api.btgpactual.mocks.OrderMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetCustomerOrdersTest {
    @InjectMocks
    private GetCustomerOrders getAllOrdersUseCase;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderQueryRepository orderQueryRepository;

    @Test
    public void getCustomerOrdersFilterCustomerIdIsNull() {
        QueryOrdersFilter filter = new QueryOrdersFilter(
                null, null, null, 10, 20);

        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> {
           getAllOrdersUseCase.getOrdersByCustomer(filter);
        });

        Assertions.assertEquals("O campo \"customerId\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void getCustomerOrdersCustomerNotFound() {
        QueryOrdersFilter filter = new QueryOrdersFilter(
                1L, null, null, 10, 20);

        Mockito.when(customerRepository.findById(filter.getCustomerId())).thenReturn(Optional.empty());

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            getAllOrdersUseCase.getOrdersByCustomer(filter);
        });

        Assertions.assertEquals("Cliente não encontrado.", exception.getMessage());
    }

    @Test
    public void getCustomerOrdersNoContent() {
        QueryOrdersFilter filter = new QueryOrdersFilter(
                1L, null, null, 10, 20);

        Mockito.when(customerRepository.findById(filter.getCustomerId())).thenReturn(Optional.of(new Customer()));
        Mockito.when(orderQueryRepository.getAll(filter)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(NoContentException.class, () -> {
            getAllOrdersUseCase.getOrdersByCustomer(filter);
        });
    }

    @Test
    public void getCustomerOrdersWithContent() throws ValidationException, NotFoundException, NoContentException {
        QueryOrdersFilter filter = new QueryOrdersFilter(
                1L, null, null, 10, 20);

        Mockito.when(customerRepository.findById(filter.getCustomerId())).thenReturn(Optional.of(new Customer()));
        Mockito.when(orderQueryRepository.getAll(filter)).thenReturn(OrderMock.OrdersDTOMock());

        var orders = getAllOrdersUseCase.getOrdersByCustomer(filter);

        Assertions.assertEquals(OrderMock.OrdersDTOMock(), orders.getData());
        Assertions.assertEquals(filter.getPage(), orders.getPage());
        Assertions.assertEquals(filter.getPageSize(), orders.getPageSize());
    }
}

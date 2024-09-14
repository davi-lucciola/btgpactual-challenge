package io.api.btgpactual.usecases.queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.api.btgpactual.core.usecases.queries.GetAllOrders;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.exceptions.NoContentException;
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

@ExtendWith(MockitoExtension.class)
public class GetAllOrdersTest {
    @InjectMocks
    private GetAllOrders getAllOrdersUseCase;

    @Mock
    private OrderQueryRepository orderQueryRepository;


    @Test
    public void getAllOrdersNoContent() {
        var filter = new QueryOrdersFilter(-1L, null, null);
        Mockito.when(orderQueryRepository.getAll(filter)).thenReturn(new ArrayList<>());

        NoContentException exception = Assertions.assertThrows(NoContentException.class, () -> getAllOrdersUseCase.getAllOrders(filter));

        Assertions.assertEquals(null, exception.getMessage());
    }

    @Test
    public void getAllOrdersWithContent() throws JsonProcessingException, NoContentException {
        var filter = new QueryOrdersFilter(null, null, null);
        Mockito.when(orderQueryRepository.getAll(filter)).thenReturn(OrderMock.ordersDTO());

        var ordersDTO = getAllOrdersUseCase.getAllOrders(filter);

        Assertions.assertEquals(Boolean.FALSE, ordersDTO.isEmpty());
    }

}

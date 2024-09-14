package io.api.btgpactual.usecases.queries;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.api.btgpactual.core.usecases.queries.DetailOrder;
import io.api.btgpactual.domain.dto.queries.OrderDetailDTO;
import io.api.btgpactual.domain.exceptions.NotFoundException;
import io.api.btgpactual.infra.repositories.queries.OrderQueryRepository;
import io.api.btgpactual.mocks.OrderMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DetailOrderTest {
    @InjectMocks
    private DetailOrder detailOrderUseCase;

    @Mock
    private OrderQueryRepository orderQueryRepository;

    @Test
    public void detailOrderNotFound() {
        var orderId = 1L;
        Mockito.when(orderQueryRepository.getById(orderId)).thenReturn(null);

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
           detailOrderUseCase.detailOrder(orderId);
        });

        Assertions.assertEquals("Pedido não encontrado.", exception.getMessage());
    }

    @Test
    public void detailOrderSuccessfully() throws JsonProcessingException, NotFoundException {
        var orderId = 1L;
        Mockito.when(orderQueryRepository.getById(orderId)).thenReturn(OrderMock.OrderDetailDTOMock());

//        OrderDetailDTO orderDetailDTO = detailOrderUseCase.detailOrder(orderId);

//        Assertions.assertEquals("Pedido não encontrado.", exception.getMessage());
    }
}

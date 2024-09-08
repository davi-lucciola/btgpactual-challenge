package io.api.btgpactual.dto;

import io.api.btgpactual.domain.dto.CreateOrderDTO;
import io.api.btgpactual.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static io.api.btgpactual.mocks.OrderItemsMock.createOrderItemsDTO;

@ExtendWith(MockitoExtension.class)
public class CreateOrderDTOTest {

    @Test
    public void testValidateNoOrderId() {
        CreateOrderDTO orderDTO = new CreateOrderDTO(
                null, 3L, createOrderItemsDTO());

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderDTO::validate);

        Assertions.assertEquals("O campo \"orderId\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void testValidateNoCustomerId() {
        CreateOrderDTO orderDTO = new CreateOrderDTO(
                2L, null, createOrderItemsDTO());

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderDTO::validate);

        Assertions.assertEquals("O campo \"customerId\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void testValidateNoItems() {
        CreateOrderDTO orderDTO = new CreateOrderDTO(
                2L, 3L, null);

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderDTO::validate);

        Assertions.assertEquals("O campo \"items\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void testValidateEmptyItems() {
        CreateOrderDTO orderDTO = new CreateOrderDTO(
                2L, 3L, new ArrayList<>());

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderDTO::validate);

        Assertions.assertEquals("O campo \"items\" é obrigatório.", exception.getMessage());
    }
}

package io.api.btgpactual.dto;

import io.api.btgpactual.domain.dto.CreateOrderItemDTO;
import io.api.btgpactual.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class CreateOrderItemDTOTest {
    @Test
    public void testValidateNoProduct() {
        CreateOrderItemDTO orderItemDTO = new CreateOrderItemDTO(null, 2, BigDecimal.ONE);

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderItemDTO::validate);

        Assertions.assertEquals("O campo \"product\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void testValidateBlankProduct() {
        CreateOrderItemDTO orderItemDTO = new CreateOrderItemDTO("  ", 2, BigDecimal.ONE);

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderItemDTO::validate);

        Assertions.assertEquals("O campo \"product\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void testValidateNoQuantity() {
        CreateOrderItemDTO orderItemDTO = new CreateOrderItemDTO("Notebook Nitro 5", null, BigDecimal.ONE);

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderItemDTO::validate);

        Assertions.assertEquals("O campo \"quantity\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void testValidateQuantityLowerOrEqualZero() {
        CreateOrderItemDTO orderItemDTO = new CreateOrderItemDTO("Notebook Nitro 5", 0, BigDecimal.ONE);

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderItemDTO::validate);

        Assertions.assertEquals("O campo \"quantity\" deve ser maior que zero.", exception.getMessage());
    }

    @Test
    public void testValidateNoPrice() {
        CreateOrderItemDTO orderItemDTO = new CreateOrderItemDTO("Notebook Nitro 5", 2, null);

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderItemDTO::validate);

        Assertions.assertEquals("O campo \"price\" é obrigatório.", exception.getMessage());
    }

    @Test
    public void testValidateLowerZero() {
        CreateOrderItemDTO orderItemDTO = new CreateOrderItemDTO("Notebook Nitro 5", 2, BigDecimal.valueOf(-3L));

        ValidationException exception = Assertions.assertThrows(
                ValidationException.class, orderItemDTO::validate);

        Assertions.assertEquals("O campo \"price\" deve ser maior ou igual a zero.", exception.getMessage());
    }
}

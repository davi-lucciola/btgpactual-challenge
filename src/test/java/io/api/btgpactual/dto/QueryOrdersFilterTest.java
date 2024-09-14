package io.api.btgpactual.dto;

import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class QueryOrdersFilterTest {
    @Test
    public void validadeCustomerIdIsNotNull() {
        var filter = new QueryOrdersFilter(null, null, null);

        ValidationException exception = Assertions.assertThrows(ValidationException.class, filter::validateCustomerIsNotNull);

        Assertions.assertEquals("O campo \"customerId\" é obrigatório.", exception.getMessage());
    }
}

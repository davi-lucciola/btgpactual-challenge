package io.api.btgpactual.mocks;

import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;

public class CustomerMock {
    public static CreateCustomerDTO CreateCustomerDTOMock() {
        return new CreateCustomerDTO("Test Customer");
    }
}

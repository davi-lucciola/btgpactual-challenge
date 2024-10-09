package io.api.btgpactual.application;

import io.api.btgpactual.core.services.CustomerService;
import io.api.btgpactual.core.services.OrderService;
import io.api.btgpactual.core.usecases.commands.CreateNewCustomer;
import io.api.btgpactual.core.usecases.commands.ProcessOrder;
import io.api.btgpactual.core.usecases.queries.DetailOrder;
import io.api.btgpactual.core.usecases.queries.GetAllOrders;
import io.api.btgpactual.core.usecases.queries.GetCustomerOrders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public OrderService orderService(
            GetAllOrders getAllUseCase,
            DetailOrder detailUseCase,
            ProcessOrder createNewUseCase
    ) {
        return new OrderService(getAllUseCase, detailUseCase, createNewUseCase);
    }

    @Bean
    public CustomerService customerService(GetCustomerOrders getCustomerOrders, CreateNewCustomer createNewCustomer) {
        return new CustomerService(getCustomerOrders, createNewCustomer);
    }
}

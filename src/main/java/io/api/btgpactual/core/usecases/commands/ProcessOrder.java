package io.api.btgpactual.core.usecases.commands;

import io.api.btgpactual.domain.dto.command.CreateOrderDTO;
import io.api.btgpactual.domain.entities.Customer;
import io.api.btgpactual.domain.entities.Order;
import io.api.btgpactual.domain.exceptions.DomainException;
import io.api.btgpactual.domain.exceptions.ValidationException;
import io.api.btgpactual.infra.repositories.commands.CustomerRepository;
import io.api.btgpactual.infra.repositories.commands.OrderItemRepository;
import io.api.btgpactual.infra.repositories.commands.OrderRepository;
import io.api.btgpactual.utils.annotations.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ProcessOrder {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Order processOrder(CreateOrderDTO createOrderDTO) throws DomainException, ValidationException {
        createOrderDTO.validate();

        Customer customer = customerRepository.findById(createOrderDTO.customerId()).orElse(null);

        if (customer == null) {
            throw new DomainException("Cliente não encontrado.");
        }

        Order order = orderRepository.findById(createOrderDTO.orderId()).orElse(null);

        if (order != null) {
            orderItemRepository.deleteByOrderId(order.getId());
            orderRepository.delete(order);
        }

        order = new Order(createOrderDTO);
        order = orderRepository.saveAndFlush(order);

        order.setOrderItems(createOrderDTO.items());
        order.setItems(orderItemRepository.saveAllAndFlush(order.getItems()));

        return order;
    }
}

package io.api.btgpactual.core.services;

import io.api.btgpactual.core.dto.CreateOrderDTO;
import io.api.btgpactual.core.exceptions.ValidationException;
import io.api.btgpactual.entities.Customer;
import io.api.btgpactual.entities.Order;
import io.api.btgpactual.core.exceptions.DomainException;
import io.api.btgpactual.repositories.commands.CustomerRepository;
import io.api.btgpactual.repositories.commands.OrderItemRepository;
import io.api.btgpactual.repositories.commands.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Order createNewOrder(CreateOrderDTO createOrderDTO) throws DomainException, ValidationException {
        createOrderDTO.validate();
        Customer customer = customerRepository.findById(createOrderDTO.customerId()).orElse(null);

        if (customer == null) {
            throw new DomainException("Cliente nao encontrado.");
        }

        Order order = orderRepository.findById(createOrderDTO.orderId()).orElse(null);
        if (order != null) {
            throw new DomainException("Pedido já registrado.");
        }

        order = new Order(createOrderDTO);
        order = orderRepository.saveAndFlush(order);

        order.setItems(createOrderDTO.items());
        orderItemRepository.saveAll(order.getItems());

        return order;
    }
}

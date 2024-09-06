package io.api.btgpactual.entities;

import io.api.btgpactual.core.dto.CreateOrderDTO;
import io.api.btgpactual.core.dto.CreateOrderItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders", schema = "public")
public class Order {
    @Id
    private Long id;

    @Column
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;

    public Order(Long id) {

    }

    public Order(CreateOrderDTO createOrderDTO) {
        this.id = createOrderDTO.orderId();
        this.customer = new Customer(createOrderDTO.customerId());
        this.total = createOrderDTO.items().stream().map(CreateOrderItemDTO::price).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setItems(List<CreateOrderItemDTO> items) {
        this.items = items.stream().map(OrderItem::new).toList();
        this.items.forEach(orderItem -> orderItem.setOrder(this));
    }
}

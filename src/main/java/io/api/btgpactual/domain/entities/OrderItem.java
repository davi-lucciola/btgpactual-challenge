package io.api.btgpactual.domain.entities;

import io.api.btgpactual.domain.dto.CreateOrderItemDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String product;

    @Column
    private Integer quantity;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public OrderItem(CreateOrderItemDTO createOrderItemDTO) {
        this.product = createOrderItemDTO.product();
        this.quantity = createOrderItemDTO.quantity();
        this.price = createOrderItemDTO.price();
    }
}

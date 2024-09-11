package io.api.btgpactual.infra.repositories.commands;

import io.api.btgpactual.domain.entities.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM OrderItem oi WHERE oi.order.id = ?1")
    void deleteByOrderId(Long orderId);
}

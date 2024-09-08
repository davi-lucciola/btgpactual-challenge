package io.api.btgpactual.infra.repositories.queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public void listarPedidos() {

    }
}

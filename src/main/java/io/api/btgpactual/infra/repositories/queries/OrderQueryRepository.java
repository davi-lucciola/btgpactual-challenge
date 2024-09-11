package io.api.btgpactual.infra.repositories.queries;

import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.QueryOrdersFilter;
import io.api.btgpactual.infra.repositories.sql.OrderSQL;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    @PersistenceContext
    private final EntityManager entityManager;
    private final Logger logger = LoggerFactory.getLogger(OrderQueryRepository.class);

    public List<OrderDTO> getAll(QueryOrdersFilter filter) {
        String sql = OrderSQL.ORDER_QUERY;

        StringBuilder where = new StringBuilder();
        if (filter.customerId() != null) {
            where.append(" AND c.id = :customerId ");
        }

        if (filter.minTotal() != null) {
            where.append(" AND o.total >= :minTotal ");
        }

        if (filter.maxTotal() != null) {
            where.append(" AND o.total <= maxTotal ");
        }

        sql = sql.replace(":WHERE", where.toString());
        Query query = entityManager.createNativeQuery(sql);

        if (filter.customerId() != null) {
            query.setParameter("customerId", filter.customerId());
        }

        if (filter.minTotal() != null) {
            query.setParameter("minTotal", filter.minTotal());
        }

        if (filter.maxTotal() != null) {
            query.setParameter("maxTotal", filter.maxTotal());
        }

        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(OrderDTO.class))
                .getResultList();
    }

    public OrderDTO getById(Long orderId) {
        try {
            String sql = OrderSQL.ORDER_QUERY.replace(
                    ":WHERE", " AND o.id = :orderId ");

            return (OrderDTO) entityManager.createNativeQuery(sql).unwrap(org.hibernate.query.Query.class)
                    .setParameter("orderId", orderId)
                    .setResultTransformer(Transformers.aliasToBean(OrderDTO.class))
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

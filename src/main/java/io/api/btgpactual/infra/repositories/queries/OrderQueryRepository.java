package io.api.btgpactual.infra.repositories.queries;

import io.api.btgpactual.domain.dto.queries.OrderDTO;
import io.api.btgpactual.domain.dto.queries.OrderDetailDTO;
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

    @SuppressWarnings({"unchecked", "deprecated"})
    public List<OrderDTO> getAll(QueryOrdersFilter filter) {
        String sql = OrderSQL.ORDER_PAGINATION_QUERY;

        StringBuilder where = new StringBuilder();
        if (filter.getCustomerId() != null) {
            where.append(" AND c.id = :customerId ");
        }

        if (filter.getMinTotal() != null) {
            where.append(" AND o.total >= :minTotal ");
        }

        if (filter.getMaxTotal() != null) {
            where.append(" AND o.total <= maxTotal ");
        }

        sql = sql.replace(":WHERE", where.toString());

        Query query = entityManager.createNativeQuery(sql)
                .setParameter("page", filter.getPage())
                .setParameter("pageSize", filter.getPageSize());

        if (filter.getCustomerId() != null) {
            query.setParameter("customerId", filter.getCustomerId());
        }

        if (filter.getMinTotal() != null) {
            query.setParameter("minTotal", filter.getMinTotal());
        }

        if (filter.getMaxTotal() != null) {
            query.setParameter("maxTotal", filter.getMaxTotal());
        }

        return query.unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(Transformers.aliasToBean(OrderDTO.class))
                .getResultList();
    }

    @SuppressWarnings({"unchecked", "deprecated"})
    public OrderDetailDTO getById(Long orderId) {
        try {
            String sql = OrderSQL.ORDER_DETAIL_QUERY.replace(
                    ":WHERE", " AND o.id = :orderId ");

            return (OrderDetailDTO) entityManager.createNativeQuery(sql)
                    .unwrap(org.hibernate.query.Query.class)
                    .setParameter("orderId", orderId)
                    .setResultTransformer(Transformers.aliasToBean(OrderDetailDTO.class))
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // OBS: Apesar o SetResultTransformer estar depreciado, a outra alternativa (TupleTransformer)
    // Seria necessário implementar o transformador na mão. Por isso preferi utilizar o "AliasToBean"
}

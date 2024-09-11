package io.api.btgpactual.infra.repositories.sql;

public class OrderSQL {
    public static String ORDER_QUERY = """
                SELECT
                  o.id "orderId",
                  c.id as "customerId",
                  c.name as customer,
                  o.total as total,
                  COUNT(oi.id) as "itemQuantity",
                  JSONB_AGG(
                    TO_JSONB(oi.*) - 'order_id'
                  ) as items
                FROM
                  orders o
                  INNER JOIN customers c
                    ON o.customer_id = c.id
                  INNER JOIN order_items oi
                    ON o.id = oi.order_id
                WHERE 1 = 1
                    :WHERE
                GROUP BY o.id, c.id, c.name, o.total
                """;
}

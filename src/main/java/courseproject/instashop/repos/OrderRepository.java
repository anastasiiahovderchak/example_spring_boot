package courseproject.instashop.repos;

import courseproject.instashop.models.Cart;
import courseproject.instashop.models.Order;
import courseproject.instashop.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query(value = "insert into orders value(summary_price = (select sum(price) as summary_price from cart))", nativeQuery = true)
    double findSummary();
}

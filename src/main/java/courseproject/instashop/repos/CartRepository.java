package courseproject.instashop.repos;

import courseproject.instashop.models.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {
    Cart findByName(String name);

    @Query(value = "select sum(price) as summary from cart", nativeQuery = true)
    double findSummary();
}

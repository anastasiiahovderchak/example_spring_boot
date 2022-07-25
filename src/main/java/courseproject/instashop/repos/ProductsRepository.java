package courseproject.instashop.repos;

import courseproject.instashop.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends CrudRepository<Product, Long> {
    @Query(value = "select * from product where name like %:name%", nativeQuery = true)
    List<Product> findByKeyword(@Param("name") String name);
}

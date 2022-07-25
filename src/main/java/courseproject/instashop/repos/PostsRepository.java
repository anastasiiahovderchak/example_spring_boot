package courseproject.instashop.repos;

import courseproject.instashop.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Long> {
}

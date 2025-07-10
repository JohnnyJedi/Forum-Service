package telran.java58.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.forum.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ForumRepository extends MongoRepository<Post, String> {

    List<Post> findByAuthorIgnoreCase(String author);

    List<Post> findByTagsInIgnoreCase(Set<String> tags);


    List<Post> findByDateCreatedBetween(LocalDateTime dateCreatedAfter, LocalDateTime dateCreatedBefore);
}

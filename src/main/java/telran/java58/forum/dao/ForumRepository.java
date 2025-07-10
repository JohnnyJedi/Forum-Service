package telran.java58.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.forum.dto.PostDto;
import telran.java58.forum.model.Post;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ForumRepository extends MongoRepository<Post, String> {

    List<Post> findByAuthorIgnoreCase(String author);

    List<Post> findByTagsMatches(Set<String> tags);

    List<Post> findByTagsIn(Set<String> tags);
}

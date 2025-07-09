package telran.java58.forum.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.forum.model.Post;

public interface ForumRepository extends MongoRepository<Post, String> {

}

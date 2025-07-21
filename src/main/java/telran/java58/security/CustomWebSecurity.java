package telran.java58.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.java58.forum.dao.ForumRepository;
import telran.java58.forum.model.Post;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {
    private final ForumRepository repository;

    public boolean isPostAuthor(String username, String postId) {
        Post post = repository.findById(postId).orElse(null);
        return post != null && post.getAuthor().equalsIgnoreCase(username);
    }
}

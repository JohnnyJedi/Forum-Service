package telran.java58.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    private LocalDateTime dateCreated = LocalDateTime.now();
    @Setter
    private Set<String> tags = new HashSet<>();
    private int likes;
    private List<Comment> comments = new ArrayList<>();

    public Post(String title, String content, String author, Set<String> tags) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addLike() {
        this.likes++;
    }

    public boolean addTags(Set<String> tags) {
       return this.tags.addAll(tags);
    }
    public boolean removeTags(Set<String> tags) {
        return this.tags.remove(tags);
    }

}

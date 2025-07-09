package telran.java58.forum.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class Comment {
    @Setter
    private String user;
    @Setter
    private String message;
    private LocalDateTime dateCreated;
    private int likes;

    public Comment(String user, String message) {
        this.user = user;
        this.message = message;
        this.dateCreated = LocalDateTime.now();
    }

    public void addLike() {
        this.likes++;
    }
}

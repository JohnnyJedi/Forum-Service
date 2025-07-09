package telran.java58.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String user;
    private String message;
    private LocalDateTime dateCreated;
    private int likes;
}

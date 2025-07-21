package telran.java58.forum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "message is required")
    @Size(min = 4, message = "message should be at least 4 symbols")
    String message;
}

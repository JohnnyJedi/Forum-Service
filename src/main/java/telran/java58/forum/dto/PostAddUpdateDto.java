package telran.java58.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostAddUpdateDto {
    private String title;
    private String content;
    private Set<String> tags;
}

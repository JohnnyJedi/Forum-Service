package telran.java58.accounting.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @Size(min = 2, max = 20, message = "login must be between 2 and 20 characters")
    private String firstName;
    @Size(min = 2, max = 20, message = "login must be between 2 and 20 characters")
    private String lastName;
}

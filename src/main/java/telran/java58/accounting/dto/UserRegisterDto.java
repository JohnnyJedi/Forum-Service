package telran.java58.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;

}

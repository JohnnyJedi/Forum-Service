package telran.java58.accounting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java58.accounting.Roles;

import java.util.Set;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRoleDto {
    private String login;
    private Set<Roles> roles;
}

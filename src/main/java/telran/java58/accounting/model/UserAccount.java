package telran.java58.accounting.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.java58.accounting.Roles;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "accounts")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"login"})
@Builder
public class UserAccount {
    @Setter
    @Id
    private String login;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
 
    private String password;
    @Singular
    private Set<Roles> roles = new HashSet<>();
    @Setter
    private LocalDateTime passwordExpirationDate = LocalDateTime.now().plusDays(60L);

    public void setPassword(String password) {
        this.password = password;
        this.passwordExpirationDate = LocalDateTime.now().plusDays(60L);
    }

    public void addRole(Roles role) {
        roles.add(role);
    }

    public void removeRole(Roles role) {
        roles.remove(role);
    }
}

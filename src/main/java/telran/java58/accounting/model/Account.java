package telran.java58.accounting.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import telran.java58.accounting.Roles;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "accounts")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"login"})
public class Account {
    @Setter
    @Id
    private String login;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String password;
    private Set<Roles> roles = new HashSet<>();

    public Account(String login, String firstName, String lastName, String password, Set<Roles> roles) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }
    public void addRole(Roles role) {
        roles.add(role);
    }
    public void removeRole(Roles role) {
        roles.remove(role);
    }
}

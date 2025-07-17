package telran.java58.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.accounting.model.UserAccount;

public interface UserAccountRepository extends MongoRepository <UserAccount, String>{


    boolean existsByLogin(String login);

    UserAccount findByLogin(String login);
}

package telran.java58.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.accounting.model.Account;

public interface AccountRepository extends MongoRepository <Account, String>{


    boolean existsByLogin(String login);

    Account findByLogin(String login);
}

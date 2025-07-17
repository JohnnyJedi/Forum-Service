package telran.java58.accounting.dto.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@NoArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
public class UserExistsException extends RuntimeException{
    public UserExistsException(String message) {
        super(message);
    }
}

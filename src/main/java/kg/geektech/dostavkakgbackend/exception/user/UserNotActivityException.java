package kg.geektech.dostavkakgbackend.exception.user;

import kg.geektech.dostavkakgbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotActivityException extends BaseException {
    public UserNotActivityException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}

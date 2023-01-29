package kg.geektech.geektechfinalprojectbackend.exception.user;

import kg.geektech.geektechfinalprojectbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class SignInException extends BaseException {
    public SignInException(String message, HttpStatus status) {
        super(message, status);
    }
}

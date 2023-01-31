package kg.geektech.dostavkakgbackend.exception.common;

import kg.geektech.dostavkakgbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}

package kg.geektech.geektechfinalprojectbackend.exception.common;

import kg.geektech.geektechfinalprojectbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}

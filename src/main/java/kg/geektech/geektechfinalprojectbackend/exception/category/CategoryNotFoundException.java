package kg.geektech.geektechfinalprojectbackend.exception.category;

import kg.geektech.geektechfinalprojectbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}

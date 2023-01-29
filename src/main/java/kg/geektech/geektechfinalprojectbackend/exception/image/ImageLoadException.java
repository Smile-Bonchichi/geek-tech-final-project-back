package kg.geektech.geektechfinalprojectbackend.exception.image;

import kg.geektech.geektechfinalprojectbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ImageLoadException extends BaseException {
    public ImageLoadException(String message, HttpStatus status) {
        super(message, status);
    }
}

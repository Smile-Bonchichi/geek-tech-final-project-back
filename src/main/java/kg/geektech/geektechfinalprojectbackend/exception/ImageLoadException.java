package kg.geektech.geektechfinalprojectbackend.exception;

import org.springframework.http.HttpStatus;

public class ImageLoadException extends BaseException{
    public ImageLoadException(String message, HttpStatus status) {
        super(message, status);
    }
}

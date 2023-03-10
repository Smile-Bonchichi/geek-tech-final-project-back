package kg.geektech.dostavkakgbackend.exception.image;

import kg.geektech.dostavkakgbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ImageLoadException extends BaseException {
    public ImageLoadException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

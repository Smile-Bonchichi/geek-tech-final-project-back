package kg.geektech.dostavkakgbackend.exception.file;

import kg.geektech.dostavkakgbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class FileException extends BaseException {
    public FileException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

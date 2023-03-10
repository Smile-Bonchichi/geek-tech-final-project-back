package kg.geektech.dostavkakgbackend.exception.mail;

import kg.geektech.dostavkakgbackend.exception.BaseException;
import org.springframework.http.HttpStatus;

public class MailException extends BaseException {
    public MailException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

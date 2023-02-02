package kg.geektech.dostavkakgbackend.exception.handler;

import kg.geektech.dostavkakgbackend.dto.BaseResponse;
import kg.geektech.dostavkakgbackend.enums.ResultCode;
import kg.geektech.dostavkakgbackend.exception.BaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<BaseResponse> getExceptionMessage(final BaseException e) {
        return buildBaseResponseMessage(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> getAuthenticationExceptionMessage(final AuthenticationException e) {
        return buildBaseResponseMessage(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException() {
        return buildBaseResponseMessage("Доступ запрещен", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(final MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> errors.put(
                ((FieldError) error).getField(),
                error.getDefaultMessage()
        ));

        return buildBaseResponseMessage(String.join(" : ", "Недопустимые значения", errors.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        return buildBaseResponseMessage(
                String.join(
                        " - ",
                        "Нарушение уникальности",
                        e.getMostSpecificCause().getMessage().substring(
                                e.getMostSpecificCause().getMessage().lastIndexOf("Ключ")
                        )
                ),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    private ResponseEntity<BaseResponse> buildBaseResponseMessage(String message, HttpStatus status) {
        return new ResponseEntity<>(
                BaseResponse.builder()
                        .resultCode(ResultCode.EXCEPTION)
                        .details(message)
                        .build(),
                status
        );
    }
}

package kg.geektech.geektechfinalprojectbackend.exception.handler;

import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> getExceptionMessage(BaseException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(buildBaseResponseMessage(e.getMessage()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse> getAuthenticationExceptionMessage(AuthenticationException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(buildBaseResponseMessage(e.getMessage()));
    }

    private BaseResponse buildBaseResponseMessage(String message) {
        return new BaseResponse() {
            @Override
            public String getMessage() {
                return message;
            }
        };
    }
}

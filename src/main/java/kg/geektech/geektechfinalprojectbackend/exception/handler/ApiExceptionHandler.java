package kg.geektech.geektechfinalprojectbackend.exception.handler;

import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.enums.ResultCode;
import kg.geektech.geektechfinalprojectbackend.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> getExceptionMessage(BaseException e) {
        return buildBaseResponseMessage(e.getMessage(), e.getStatus());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse> getAuthenticationExceptionMessage(AuthenticationException e) {
        return buildBaseResponseMessage(e.getMessage(), HttpStatus.UNAUTHORIZED);
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

package kg.geektech.geektechfinalprojectbackend.exception.handler;

import kg.geektech.geektechfinalprojectbackend.dto.BaseResponse;
import kg.geektech.geektechfinalprojectbackend.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse> getExceptionMessage(BaseException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(
                        new BaseResponse() {
                            @Override
                            public String getMessage() {
                                return e.getMessage();
                            }
                        }
                );
    }
}

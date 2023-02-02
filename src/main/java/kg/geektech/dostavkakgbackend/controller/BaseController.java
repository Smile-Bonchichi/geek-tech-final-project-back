package kg.geektech.dostavkakgbackend.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kg.geektech.dostavkakgbackend.dto.BaseResponse;
import kg.geektech.dostavkakgbackend.enums.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ApiResponses(value = {
        @ApiResponse(
                responseCode = "400",
                description = "Не коректные данные",
                content = {
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = BaseResponse.class)
                        )
                }),
        @ApiResponse(
                responseCode = "500",
                description = "Ошибка на сервере",
                content = {
                        @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = BaseResponse.class)
                        )
                })
})
public abstract class BaseController {
    protected <T> ResponseEntity<BaseResponse> constructSuccessResponse(T result) {
        return new ResponseEntity<>(
                BaseResponse.builder()
                        .result(result)
                        .resultCode(ResultCode.SUCCESS)
                        .build(),
                HttpStatus.OK
        );
    }

    protected <T> ResponseEntity<BaseResponse> constructSuccessResponse(T result, String details) {
        return new ResponseEntity<>(
                BaseResponse.builder()
                        .result(result)
                        .resultCode(ResultCode.SUCCESS)
                        .details(details)
                        .build(),
                HttpStatus.OK
        );
    }

    protected <T> ResponseEntity<BaseResponse> constructFailResponse(String details) {
        return new ResponseEntity<>(
                BaseResponse.builder()
                        .resultCode(ResultCode.FAIL)
                        .details(details)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    protected <T> ResponseEntity<BaseResponse> constructFailResponse(T errorResult, String details) {
        return new ResponseEntity<>(
                BaseResponse.builder()
                        .result(errorResult)
                        .resultCode(ResultCode.FAIL)
                        .details(details)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}

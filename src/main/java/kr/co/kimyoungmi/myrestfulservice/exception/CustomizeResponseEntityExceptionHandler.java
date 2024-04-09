package kr.co.kimyoungmi.myrestfulservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice // AOP 적용: 관점을 기준으로 모듈화 => 모든 컨트롤러 빈이 Exception 발생시 실행
public class CustomizeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    // Exception ex: 예외 객체, WebRequest request: 서비스 상태에서 발생할 수 있는 요청 정보
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(), // 예외 발생 시간
                ex.getMessage(), // 예외 메세지 - 예외의 title 정도만 가지고 있음
                request.getDescription(false) // 상세 정보를 제외 시킴: uri=/users/12312" 정도의 요청 정보만 나감
        );

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                "Validation failed",
                ex.getBindingResult().toString()
        );

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}

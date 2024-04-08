package kr.co.kimyoungmi.myrestfulservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse { // 일반화 된 예외 객체로 사용
    private Date timestamp;
    private String message;
    private String details;
}

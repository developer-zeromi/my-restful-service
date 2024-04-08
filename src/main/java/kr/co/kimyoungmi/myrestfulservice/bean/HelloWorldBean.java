package kr.co.kimyoungmi.myrestfulservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data // Lombok. Setter, Getter 메서드 같이 만들어짐 => 자동 생성 메서드 Structure 숏컷에서 확인 가능
@AllArgsConstructor // Lombok. 멤버 변수 전체를 인자로 받는 생성자 자동 생성
public class HelloWorldBean {
    private final String message;

//    public HelloWorldBean(String message) {
//        this.message = message;
//    }
}

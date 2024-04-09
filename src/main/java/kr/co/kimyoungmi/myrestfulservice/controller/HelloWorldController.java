package kr.co.kimyoungmi.myrestfulservice.controller;

import kr.co.kimyoungmi.myrestfulservice.bean.HelloWorldBean;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController // 스프링 컨텍스트에 빈으로 등록
@RequiredArgsConstructor
public class HelloWorldController {
    // 다국어 처리용 빈 주입
    private final MessageSource messageSource;

    // GET
    // URI - /hello-world
    // @RequestMapping(method=RequestMethod.GET, path="/hello-world")
    @GetMapping(path = "/hello-world")
    public String  helloWorld() {
        return "Hello World"; // 문자열 직접 반환
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World!"); // JSON 포맷의 오브젝트로 전달
    }

    // hello-world-bean/path-variable/kennet
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    @GetMapping(path = "/hello-world-internationalized") // Accept-Language 라는 헤더를 Locale 타입으로 요청자에게 받음
    public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale); // 가져 올 메세지
    }
}

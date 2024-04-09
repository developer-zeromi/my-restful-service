package kr.co.kimyoungmi.myrestfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class MyRestfulServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyRestfulServiceApplication.class, args);

//         스프링 컨텍스트 획득
//        ApplicationContext ac = SpringApplication.run(MyRestfulServiceApplication.class, args);

//         스프링 컨텍스트에 추가 된 빈 명칭 확인
//        String[] allBeanNames = ac.getBeanDefinitionNames();
//        for (String beanName : allBeanNames) {
//            System.out.println("beanName = " + beanName);
//        }
    }

    @Bean // 다국어 처리용 빈 등록
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US); // 기본 Locale 값 지정
        return localeResolver;
    }

}

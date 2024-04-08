package kr.co.kimyoungmi.myrestfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

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

}

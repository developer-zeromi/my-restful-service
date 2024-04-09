package kr.co.kimyoungmi.myrestfulservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class SecurityConfig {
    @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        UserDetails newUser = User.withUsername("user")
                .password(passwordEncoder().encode("passw0rd"))
                .authorities("read")
                .build();

        userDetailsManager.createUser(newUser);
        return userDetailsManager;
    }

    // 인코딩 할 수 있는 알고리즘 객체
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 인증 무시하고자 하는 옵션 지정
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }

    @Bean // 리소스의 변경을 가져오는 작업은 시큐리티 필터 체인을 빈으로 등록해 작업 필요
    protected SecurityFilterChain filterChain(HttpSecurity http,
                                              HandlerMappingIntrospector introspector) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}

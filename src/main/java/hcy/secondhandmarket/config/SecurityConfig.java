package hcy.secondhandmarket.config;

import hcy.secondhandmarket.security.filter.ApiCheckFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Authentication (인증) : 사용자가 자신의 신분증으로 자신을 증명한다.
 * Authorization (인가) : 서버에서 사용자에게 일종의 '권한'을 부여해준다.
 *
 * 스프링 시큐리티 내부에서는 여러 Filter가 Chain으로 존재
 * 각 Filter 마다 Request를 처리. (Servlet과 비슷한 느낌)
 * Filter는 AuthenticationManager를 통해 Authentication(인증) 처리
 * AuthenticationManager에 있는 인증 처리 메서드는 리턴 타입과 파라미터가 둘다 Authentication 타입임
 * Authentication 타입에서 실제 전달 되는 구현체들은 Token 객체로 전달됨 (ex) UsernamePasswordAuthenticationToken)
 * (스프링 시큐리티의 주요 역할이 인증 관련 정보를 Token 객체로 만들어서 전달한다는 점)
 *
 * 실제 Filter에서는 Request를 통해 사용자 ID와 Password를 받은 뒤 이를 이용해 Token 객체를 만들고
 * 이를 AuthenticationManager의 authenticate() 에 파라미터로 전달함
 *
 * Authentication 에서는 다양한 방식으로 인증 처리 방법을 제공함 (데이터베이스 이용? 메모리 이용?)
 * -> 이를 AuthenticationProvider로 처리. 전달되는 토큰 타입을 처리할 수 있는 존재인지 확인 후 authenticate()를 수행함..
 *
 * 토큰을 처리할 수 있다면, AuthenticationProvider는 UserDetailService를 이용해서 "실제 인증을 위한 데이터를 가져오는 역할"을 수행함.
 *
 *
 * -----
 * Authorization (인가) 는 위의 인증 과정이 끝난 후 사용자가 이 URL에 있는 리소스를 읽을 권한이 있는지 적절한지 확인함.
 * 이를 authenticate() 메소드의 리턴 값인 Authentication 객체의 Roles라는 권한 정보를 통해 확인함.
 * 이 정보로 사용자가 원하는 작업을 할 수 있는지 '허가' 하게 되는데, 이러한 행위를 "Access Control" 이라고 한다.
 *
 * **/

@Configuration
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // 어노테이션 기반 접근 제한 가능하게 해줌.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /** SpringBoot 2.0 부터는 인증을 위해서 패스워드를 암호화는 PasswordEncoder를 지정해야 한다.
     * PasswordEncoder는 Interface로 그 중 구현체인 BCryptPasswordEncoder를 사용한다. **/
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 스프링 시큐리티를 이용해 특정 리소스에 접근 제한을 하는 방식은 1.설정에서 패턴 적용, 2. 어노테이션 활용
     * 여기서는 설정에서 패턴 적용을 파악해봄.
     * HttpSecurity 객체는 Builder 패턴으로 설정을 엮을 수 있음.
     *
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()  // 인증, 인가가 필요할 시 로그인 화면 출력.
                .loginPage("/members/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/");

        http.csrf().disable(); // CSRF 공격 방지 토큰 발행 비활성화.

        http.logout().deleteCookies("JSESSIONID"); // logout 기능 활성화 (/logout)

//        http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);

    }

//    @Bean
//    public ApiCheckFilter apiCheckFilter() {
//        return new ApiCheckFilter("/reviews/**/*");
//    }
}

package com.olozlo.goodcalendarapi.config.auth;

import com.olozlo.goodcalendarapi.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService memberService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()

                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .formLogin(AbstractHttpConfigurer::disable)

                .logout().logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정의 진입점

                .and()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 옵션을 disable

                .and()
                .authorizeRequests(authorize -> authorize // URL별 권한 관리를 설정하는 옵션의 시작점. 선언해야만 antMatchers 옵션 사용 가능
                        .antMatchers("/h2-console/**", "/signup").permitAll()
                        .antMatchers("/api/**").hasRole(Role.USER.name()) // USER 권한을 가진 사람만 입장 가능
                        .anyRequest().authenticated() // 설정 이외의 URL들. 인증된 사용자(로그인한 사용자)들에게만 허용
                )

                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder);
    }
}

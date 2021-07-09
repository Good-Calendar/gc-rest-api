package com.olozlo.goodcalendarapi.config;

import com.olozlo.goodcalendarapi.service.MemberService;
import com.olozlo.goodcalendarapi.web.dto.member.CreateMemberRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            MemberService memberService;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                CreateMemberRequest user = CreateMemberRequest.builder()
                        .email("user@email.com")
                        .password("pass")
                        .username("name")
                        .build();

                memberService.saveMember(user);
            }
        };

    }
}
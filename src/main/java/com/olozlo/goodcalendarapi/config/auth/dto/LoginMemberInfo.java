package com.olozlo.goodcalendarapi.config.auth.dto;

import com.olozlo.goodcalendarapi.domain.member.Member;
import lombok.Getter;

@Getter
public class LoginMemberInfo {
    private String username;
    private String email;

    public LoginMemberInfo(Member member) {
        this.username = member.getUsername();
        this.email = member.getEmail();
    }
}

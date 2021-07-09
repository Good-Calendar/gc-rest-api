package com.olozlo.goodcalendarapi.web.dto.member;

import com.olozlo.goodcalendarapi.domain.member.Member;
import com.olozlo.goodcalendarapi.domain.member.Role;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class CreateMemberRequest {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .username(username)
                .password(password)
                .role(Role.USER)
                .build();
    }
}
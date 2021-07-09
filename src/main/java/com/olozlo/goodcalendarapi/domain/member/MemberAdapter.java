package com.olozlo.goodcalendarapi.domain.member;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;

public class MemberAdapter extends User {
    private Member member;

    public MemberAdapter(Member member) {
        super(member.getEmail(), member.getPassword(), authorities(member.getRole()));
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    private static Collection<? extends GrantedAuthority> authorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
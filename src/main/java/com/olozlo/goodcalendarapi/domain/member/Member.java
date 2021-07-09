package com.olozlo.goodcalendarapi.domain.member;

import com.olozlo.goodcalendarapi.web.dto.member.UpdateMemberRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Type type = Type.NONE;

    @Builder
    public Member(Long id, String email, String username, String password, Role role, Type type) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.type = type;
    }

    public void update(UpdateMemberRequest req) {
        this.username = req.getUsername();
    }
}

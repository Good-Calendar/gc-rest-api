package com.olozlo.goodcalendarapi.service;

import com.olozlo.goodcalendarapi.web.dto.member.CreateMemberRequest;
import com.olozlo.goodcalendarapi.domain.member.Member;
import com.olozlo.goodcalendarapi.domain.member.MemberAdapter;
import com.olozlo.goodcalendarapi.repository.MemberRepository;
import com.olozlo.goodcalendarapi.web.dto.member.UpdateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = this.memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new MemberAdapter(member);
    }

    @Transactional
    public Member saveMember(CreateMemberRequest memberDto) {
        validateDuplicateMember(memberDto);
        memberDto.setPassword(this.passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity());
    }

    @Transactional
    public Member update(UpdateMemberRequest memberRequest) {
//        Member member = memberRepository.findByEmail();

        return null;
    }

    @Transactional
    public Member updatePassword(UpdateMemberRequest memberRequest) {
//        Member member = memberRepository.findByEmail();

        return null;
    }

    private void validateDuplicateMember(CreateMemberRequest memberDto) {
        memberRepository.findByEmail(memberDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 사용 중인 이메일입니다.");
                });
    }

}

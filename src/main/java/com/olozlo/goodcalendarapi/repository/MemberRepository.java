package com.olozlo.goodcalendarapi.repository;

import com.olozlo.goodcalendarapi.domain.member.Member;
import com.olozlo.goodcalendarapi.domain.member.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndType(String email, Type type);

}

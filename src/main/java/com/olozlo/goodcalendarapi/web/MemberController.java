package com.olozlo.goodcalendarapi.web;

import com.olozlo.goodcalendarapi.domain.member.Member;
import com.olozlo.goodcalendarapi.config.auth.LoginMember;
import com.olozlo.goodcalendarapi.service.MemberService;
import com.olozlo.goodcalendarapi.web.dto.member.CreateMemberRequest;
import com.olozlo.goodcalendarapi.web.dto.member.UpdateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity signup(@Valid @RequestBody CreateMemberRequest memberDto) {
        Member createMember = memberService.saveMember(memberDto);

        WebMvcLinkBuilder selfLinkBuilder = linkTo(MemberController.class).slash(createMember.getId());
        URI createdUri = selfLinkBuilder.toUri();

        return ResponseEntity.created(createdUri).body(createMember);
    }

    @PatchMapping("/member")
    public ResponseEntity updateMember(
            @LoginMember LoginMemberInfo memberInfo,
            @Valid @RequestBody UpdateMemberRequest memberRequest) {
        Member updatedMember = memberService.update(memberRequest);

        return null;
    }

}

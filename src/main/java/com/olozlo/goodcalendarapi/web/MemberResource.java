package com.olozlo.goodcalendarapi.web;

import com.olozlo.goodcalendarapi.domain.member.Member;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class MemberResource extends EntityModel<Member> {

    public MemberResource(Member member, Link... links) {
        of(member, links);
        add(linkTo(MemberController.class).slash(member.getId()).withSelfRel());
    }
}

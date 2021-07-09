package com.olozlo.goodcalendarapi.member;

import com.olozlo.goodcalendarapi.common.BaseTest;
import com.olozlo.goodcalendarapi.domain.member.Member;
import com.olozlo.goodcalendarapi.repository.MemberRepository;
import com.olozlo.goodcalendarapi.service.MemberService;
import com.olozlo.goodcalendarapi.web.dto.member.CreateMemberRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerTest extends BaseTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 유저_회원가입_테스트() throws Exception {
        //given
        CreateMemberRequest memberRequest = getCreateMemberRequest();

        //when
        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequest)))

                .andDo(print())
                .andExpect(status().isCreated())
        ;

        //then
        Member member = memberRepository.findByEmail(memberRequest.getEmail()).get();
        assertThat(member.getUsername()).isEqualTo(memberRequest.getUsername());
    }

    private CreateMemberRequest getCreateMemberRequest() {
        String username = "username";
        String email = "newuser@email.com";
        String password = "password";

        CreateMemberRequest memberRequest = CreateMemberRequest.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
        return memberRequest;
    }
}

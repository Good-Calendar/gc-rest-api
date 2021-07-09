package com.olozlo.goodcalendarapi.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    GOOGLE, KAKAO, NAVER, NONE
}

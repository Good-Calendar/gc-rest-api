package com.olozlo.goodcalendarapi.web.dto.member;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UpdateMemberRequest {
    @NotEmpty
    private String username;
}

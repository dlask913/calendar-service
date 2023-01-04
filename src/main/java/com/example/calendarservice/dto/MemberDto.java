package com.example.calendarservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberDto {
    private Long id;
    @Email(message = "이메일 형식으로 입력해주세요")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다")
    private String password;

    private String group;
    @NotEmpty(message = "소속 코드를 입력해주세요")
    private String groupCode;
}

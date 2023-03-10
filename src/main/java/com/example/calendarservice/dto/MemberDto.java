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
    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String password;
    @NotEmpty(message = "이름을 입력해주세요")
    private String userName;
    @NotEmpty(message = "소속을 입력해주세요")
    private String team;
    private String teamCode;
}

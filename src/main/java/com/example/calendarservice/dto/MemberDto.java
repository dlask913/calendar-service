package com.example.calendarservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberDto {
    private Long id;
    private String email;
    private String password;

    private String userName;
    private String team;
    private String teamCode;
}

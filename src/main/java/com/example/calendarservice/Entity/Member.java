package com.example.calendarservice.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Table(name = "member")
@Getter @Setter
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String company;  // 소속 , 같은 소속끼리 일정 공유
    @Enumerated(value = EnumType.STRING)
    private Role role;

}

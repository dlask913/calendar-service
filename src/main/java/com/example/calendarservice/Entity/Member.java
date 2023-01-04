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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private MemberGroup memberGroup;   // 소속 , 같은 소속끼리 일정 공유

    @Enumerated(value = EnumType.STRING)
    private Role role;

}
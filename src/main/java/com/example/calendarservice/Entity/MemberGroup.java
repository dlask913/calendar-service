package com.example.calendarservice.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "group")
@Getter @Setter
public class MemberGroup {
    @Id @Column(name = "group_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String groupName;
    private String groupCode;

    @OneToMany(mappedBy = "memberGroup", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();
}

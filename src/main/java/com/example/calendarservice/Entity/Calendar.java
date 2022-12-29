package com.example.calendarservice.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Table(name = "calendar")
@Getter @Setter @Entity
public class Calendar {
    @Id
    @Column(name = "calendar_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String startDate;
    private String endDate;
}

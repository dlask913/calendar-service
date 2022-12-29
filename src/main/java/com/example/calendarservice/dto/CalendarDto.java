package com.example.calendarservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter @Setter
public class CalendarDto {
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull(message = "시작일을 입력해주세요")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd") @NotNull(message = "종료일을 입력해주세요")
    private LocalDate endDate;
}

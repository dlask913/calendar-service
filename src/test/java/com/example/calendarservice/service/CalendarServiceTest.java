package com.example.calendarservice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.calendarservice.Entity.Calendar;
import com.example.calendarservice.dto.CalendarDto;
import com.example.calendarservice.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@SpringBootTest @Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CalendarServiceTest {
    @Autowired
    CalendarService calendarService;
    @Autowired
    CalendarRepository calendarRepository;

//    @Test
    @DisplayName("작업 저장 Test")
    public void saveToDoTest() {
        CalendarDto calendarDto = new CalendarDto();
        calendarDto.setTitle("제목");
        calendarDto.setStartDate(LocalDate.parse("2022-12-11", DateTimeFormatter.ISO_DATE));
        calendarDto.setEndDate(LocalDate.parse("2022-12-21", DateTimeFormatter.ISO_DATE));

        ModelMapper mapper = new ModelMapper();
        Calendar calendar = mapper.map(calendarDto, Calendar.class);
        calendarService.save(calendar);

        Calendar savedCalendar = calendarRepository.findById(calendar.getId()).orElseThrow(EntityNotFoundException::new);

        assertEquals(savedCalendar.getTitle(),calendarDto.getTitle());
        assertEquals(savedCalendar.getStartDate(),calendarDto.getStartDate().toString());
        assertEquals(savedCalendar.getEndDate(), calendarDto.getEndDate().toString());
    }

    public Calendar createToDo(){
        Calendar calendar = new Calendar();
        calendar.setTitle("제목");
        calendar.setStartDate("2022-12-1");
        calendar.setEndDate("2022-12-2");
        calendarRepository.save(calendar);
        return calendar;
    }

    @Test
    @DisplayName("작업 수정 테스트")
    public void updateToDoTest(){
        Calendar calendar = createToDo();
        calendar.setTitle("수정제목");
        calendar.setStartDate("수정시작일");
        calendar.setEndDate("수정종료일");
        calendarService.updateCalendar(calendar);

        Calendar savedCalendar = calendarService.findById(calendar.getId());

        assertEquals(savedCalendar.getTitle(), calendar.getTitle());
        assertEquals(savedCalendar.getStartDate(), calendar.getStartDate());
        assertEquals(savedCalendar.getEndDate(), calendar.getEndDate());

    }

    @Test
    @DisplayName("작업 삭제 테스트")
    public void deleteToDoTest(){
        Calendar calendar = createToDo();
        Long id = calendar.getId();
        calendarService.deleteCalendar(id);

        assertThrows(EntityNotFoundException.class, () -> calendarService.findById(id));
    }


}
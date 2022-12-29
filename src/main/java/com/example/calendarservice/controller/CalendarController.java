package com.example.calendarservice.controller;

import com.example.calendarservice.Entity.Calendar;
import com.example.calendarservice.dto.CalendarDto;
import com.example.calendarservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("calendar")
    public String test(Model model){
        model.addAttribute("calendarDto", new CalendarDto());
        return "calendar/calendarForm";
    }

    @PostMapping("calendar")
    public String transfer(@Valid CalendarDto calendarDto , BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "calendar/calendarForm";
        }
        ModelMapper mapper = new ModelMapper();
        Calendar calendar = mapper.map(calendarDto, Calendar.class);
        calendarService.save(calendar);
        return  "redirect:/";
    }
}

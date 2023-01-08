package com.example.calendarservice.controller;

import com.example.calendarservice.Entity.Calendar;
import com.example.calendarservice.dto.CalendarDto;
import com.example.calendarservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("calendar")
    public String standardCalendar(){
        return "standard";
    }

    @GetMapping("calendar/new")
    public String inputForm(Model model){
        model.addAttribute("calendarDto", new CalendarDto());
        return "calendar/calendarForm";
    }

    @PostMapping("calendar/new")
    public String newSchedule(@Valid CalendarDto calendarDto , BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "calendar/calendarForm";
        }
        if (calendarDto.getStartDate().isAfter(calendarDto.getEndDate())) {
            model.addAttribute("errorMessage", "시작일이 종료일보다 늦습니다.");
            return "calendar/calendarForm";
        }

        ModelMapper mapper = new ModelMapper();
        calendarDto.setEndDate(calendarDto.getEndDate().plusDays(1));
        Calendar calendar = mapper.map(calendarDto, Calendar.class);
        calendarService.save(calendar);
        return  "redirect:/";
    }

    @GetMapping(value = "calendar/{id}")
    public String updateCalendar(@PathVariable("id") Long id,Model model) {
        Calendar calendar = calendarService.findById(id);
        model.addAttribute("calendarDto", calendar);
        return "calendar/calendarForm";
    }

    @PostMapping(value = "calendar/{id}")
    public String updateCalendar(Calendar calendar) {
        calendarService.updateCalendar(calendar);
        return "redirect:/";
    }

    @PostMapping(value = "calendar/{id}/delete")
    public String deleteCalendar(@PathVariable("id") Long id){
        calendarService.deleteCalendar(id);
        return "redirect:/";
    }

//    @PostMapping(value = "calendar/update")
//    public String updateCalendar(@RequestParam("title") String title,
//                               @RequestParam("start") String start,
//                               @RequestParam("id") Long id2, Model model){
//        System.out.println("id>>>>");
//        Calendar calendar = calendarService.findById(id2);
//        model.addAttribute("calendarDto", calendar);
//        return "calendar/calendarForm";
//    }

}

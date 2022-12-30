package com.example.calendarservice.controller;

import com.example.calendarservice.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller @RequiredArgsConstructor
public class MainController {

     private final CalendarService calendarService;

    @RequestMapping(value = "/standard",method = RequestMethod.GET)
    @ResponseBody
    public String standard(Model model) throws JSONException {
        String res = calendarService.objectToJson();
//        System.out.println(">>>>>result");
//        System.out.println(res);
        return res;
    }

    @GetMapping("/")
    public String standard(){
        return "standard";
    }

    @GetMapping("/premium")
    public String premium(){
        return "premium";
    }
}

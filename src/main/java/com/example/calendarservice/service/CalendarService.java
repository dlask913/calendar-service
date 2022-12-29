package com.example.calendarservice.service;

import com.example.calendarservice.Entity.Calendar;
import com.example.calendarservice.repository.CalendarRepository;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service @Transactional @RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    public void save(Calendar calendar) {
        calendarRepository.save(calendar);
    }
    public String objectToJson(){
        List<Calendar> calendars = calendarRepository.findAll();
        JSONArray jsonArray = new JSONArray();

        for (Calendar c:
             calendars) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", c.getTitle());
            jsonObject.put("start", c.getStartDate());
            jsonObject.put("end",c.getEndDate());
            jsonArray.put(jsonObject);
        }
        String res = jsonArray.toString();
        return res;
    }
}

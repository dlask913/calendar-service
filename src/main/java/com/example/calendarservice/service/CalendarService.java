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

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service @Transactional @RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    public void save(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    public Calendar findById(Long id){
        Calendar calendar = calendarRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return calendar;
    }

    public String objectToJson(){
        List<Calendar> calendars = calendarRepository.findAll();
        JSONArray jsonArray = new JSONArray();

        for (Calendar c:
             calendars) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", c.getId());
            jsonObject.put("title", c.getTitle());
            if (c.getStartTime() != null && !(c.getStartTime().isEmpty())){
                jsonObject.put("start", c.getStartDate() + "T" + c.getStartTime());
            } else {
                jsonObject.put("start", c.getStartDate());
            }
            jsonObject.put("end",c.getEndDate());
            jsonArray.put(jsonObject);
        }
        String res = jsonArray.toString();
        return res;
    }

    public void updateCalendar(Calendar calendar){
        Calendar res = findById(calendar.getId());
        res.setTitle(calendar.getTitle());
        res.setStartDate(calendar.getStartDate());
        res.setEndDate(calendar.getEndDate());
        res.setStartTime(calendar.getStartTime());
    }

    public void deleteCalendar(Long id) {
        calendarRepository.delete(findById(id));
    }
}

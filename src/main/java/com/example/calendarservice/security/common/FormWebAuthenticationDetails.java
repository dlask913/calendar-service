package com.example.calendarservice.security.common;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class FormWebAuthenticationDetails extends WebAuthenticationDetails {
    private String teamCode;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        teamCode = request.getParameter("teamCode");
    }

    public String getTeamCode() {
        return teamCode;
    }

}

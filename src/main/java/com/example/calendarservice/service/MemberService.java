package com.example.calendarservice.service;

import com.example.calendarservice.Entity.Member;
import com.example.calendarservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional @Service
@RequiredArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;

    public void save(Member member) {
        memberRepository.save(member);
    }
}

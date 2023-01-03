package com.example.calendarservice.controller;

import com.example.calendarservice.Entity.Member;
import com.example.calendarservice.dto.MemberDto;
import com.example.calendarservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping("/member/new")
    public String memberForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/memberForm";
    }

    @PostMapping("/member/new")
    public String newMember(@Valid MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        ModelMapper mapper = new ModelMapper();

        Member member = mapper.map(memberDto, Member.class);
        memberService.save(member);
        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String loginForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/loginForm";
    }

    @PostMapping("/member/login")
    public String loginMember(MemberDto memberDto) {

        return "redirect:/";
    }
}

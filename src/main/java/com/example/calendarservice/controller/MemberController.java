package com.example.calendarservice.controller;

import com.example.calendarservice.Entity.Member;
import com.example.calendarservice.Entity.Role;
import com.example.calendarservice.Entity.Team;
import com.example.calendarservice.dto.MemberDto;
import com.example.calendarservice.dto.MemberFormDto;
import com.example.calendarservice.service.MemberService;
import com.example.calendarservice.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final TeamService teamService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping(value = {"/","member/login"})
    public String memberForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/member/new")
    public String newMember(@Valid MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }
        System.out.println("memberDto>>>" + memberDto.getEmail());
        System.out.println("memberDto>>>" + memberDto.getTeam());
        System.out.println("memberDto>>>" + memberDto.getPassword());


        ModelMapper mapper = new ModelMapper();

        Team team = teamService.findByName(memberDto.getTeam());
        Member member = mapper.map(memberDto, Member.class);
        member.setRole(Role.USER);
        member.setTeam(team);
        memberService.save(member,passwordEncoder);
        return "redirect:/";
    }

    @PostMapping("/member/new/admin")
    public String newAdminMember(MemberDto memberDto) {
//        if (bindingResult.hasErrors()) {
//            return "member/memberForm";
//        }

        ModelMapper mapper = new ModelMapper();

        Team team = new Team();
        team.setTeamCode(memberDto.getTeamCode());
        team.setTeamName(memberDto.getTeam());

        Member member = mapper.map(memberDto, Member.class);
        member.setTeam(team);
        member.setRole(Role.ADMIN);
        System.out.println("member>>>>"+member.toString());


        team.getMembers().add(member);
        teamService.saveTeam(team);
        memberService.save(member,passwordEncoder);
        return "redirect:/";
    }

    @GetMapping(value = "/member/login/error")
    public String loginError(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        model.addAttribute("memberFormDto", new MemberFormDto());
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        model.addAttribute("errorMessage", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/memberForm";
    }

}

package com.example.calendarservice.service;

import com.example.calendarservice.Entity.Member;
import com.example.calendarservice.Entity.Role;
import com.example.calendarservice.Entity.Team;
import com.example.calendarservice.dto.MemberDto;
import com.example.calendarservice.repository.MemberRepository;
import com.example.calendarservice.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional @Service
@RequiredArgsConstructor
public class MemberService{
    private final MemberRepository memberRepository;
    private final TeamService teamService;

//    public void save(MemberDto memberDto, PasswordEncoder passwordEncoder) {
//        ModelMapper mapper = new ModelMapper();
//
//        Team team = teamService.findByName(memberDto.getTeam());
//        Member member = mapper.map(memberDto, Member.class);
//        member.setRole(Role.USER);
//        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
//        member.setTeam(team);
//
//        validateDuplicateMember(member);
//        memberRepository.save(member);
//    }

    public void saveAdmin(MemberDto memberDto, PasswordEncoder passwordEncoder, Long teamId) {
        ModelMapper mapper = new ModelMapper();


        Member member = mapper.map(memberDto, Member.class);
        member.setTeam(teamService.findById(teamId));
        member.setRole(Role.ADMIN);
        member.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    public Member findByEmail(String email){
        return memberRepository.findByEmail(email);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(email);
//
//        if (member == null) {
//            throw new UsernameNotFoundException(email);
//        }
//
//        return User.builder()
//                .username(member.getEmail())
//                .password(member.getPassword())
//                .roles(member.getRole().toString())
//                .build();
//    }
}

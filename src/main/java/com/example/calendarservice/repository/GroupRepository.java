package com.example.calendarservice.repository;

import com.example.calendarservice.Entity.MemberGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<MemberGroup,Long> {
}

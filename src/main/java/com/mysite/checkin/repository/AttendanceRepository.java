package com.mysite.checkin.repository;

import com.mysite.checkin.domain.Attendance;
import com.mysite.checkin.domain.SiteUser;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    Optional<Attendance> findBySiteUser(SiteUser siteUser);

}

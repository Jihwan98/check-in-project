package com.mysite.checkin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private SiteUser siteUser;

    private LocalDate checkinDate;
    private LocalTime checkinTime;

    private String status; // 출석, 지각, 결석 등
}

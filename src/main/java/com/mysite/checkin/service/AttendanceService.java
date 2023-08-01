package com.mysite.checkin.service;

import com.mysite.checkin.DataNotFoundException;
import com.mysite.checkin.domain.Attendance;
import com.mysite.checkin.domain.SiteUser;
import com.mysite.checkin.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import javax.accessibility.AccessibleText;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final LocalTime checkTimeStart = LocalTime.of(9, 0);
    private final LocalTime checkTimeEnd = LocalTime.of(9, 11);
    public Attendance getAttendance(Integer id) {
        Optional<Attendance> attendance = this.attendanceRepository.findById(id);
        if (attendance.isPresent()) {
            return attendance.get();
        } else {
            throw new DataNotFoundException("attendance not found");
        }
    }

    public Attendance getAttendance(SiteUser user) {
        Optional<Attendance> attendance = this.attendanceRepository.findBySiteUser(user);
        if (attendance.isPresent()) {
            return attendance.get();
        } else {
            throw new DataNotFoundException("attendance not found");
        }
    }

    public void create(SiteUser user, LocalDateTime time) {
        Attendance atd = new Attendance();
        atd.setSiteUser(user);
        atd.setCheckinDate(time.toLocalDate());
        atd.setCheckinTime(time.toLocalTime());

        if (time.toLocalTime().isBefore(this.checkTimeEnd)) {
            atd.setStatus("출석");
        } else {
            atd.setStatus("지각");
        }
        this.attendanceRepository.save(atd);
    }

    public List<Attendance> findByUserAndDate(SiteUser user, LocalDate date) {
        return this.attendanceRepository.findBySiteUserAndCheckinDate(user, date);
    }
}

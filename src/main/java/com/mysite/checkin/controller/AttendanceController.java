package com.mysite.checkin.controller;

import com.mysite.checkin.domain.Attendance;
import com.mysite.checkin.domain.SiteUser;
import com.mysite.checkin.service.AttendanceService;
import com.mysite.checkin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RequestMapping("/attendance")
@RequiredArgsConstructor
@Controller
public class AttendanceController {
    private final AttendanceService attendanceService;
    private final UserService userService;
    private final LocalTime checkTimeStart = LocalTime.of(9, 0);

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/checkin")
    public String checkin(Principal principal) {
        SiteUser user = this.userService.getUser(principal.getName());

        LocalDateTime now = LocalDateTime.now();
        LocalDate date = now.toLocalDate();
        LocalTime time = now.toLocalTime();

        String status;
        if (time.isBefore(this.checkTimeStart)) {
            // ResponseStatusException으로 보내는게 맞을지..? 일단 front 에서 alert로 알려주던지..
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "9시 ~ 9시 10분에 시도하세요");
        }
        this.attendanceService.create(user, now);
        return "redirect:/";
    }
}

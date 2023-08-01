package com.mysite.checkin;

import com.mysite.checkin.domain.Attendance;
import com.mysite.checkin.domain.SiteUser;
import com.mysite.checkin.repository.UserRepository;
import com.mysite.checkin.service.AttendanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CheckinApplicationTests {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testEtc() {
		LocalDateTime t = LocalDateTime.now();
		LocalDate t2;
		LocalTime t3;

		t2 = t.toLocalDate();
		t3 = t.toLocalTime();

		System.out.println(t);
		System.out.println(t2);
		System.out.println(t3);

//		LocalTime checkTime = LocalTime.of(9, 11);
		LocalTime checkTime = LocalTime.of(11, 23);
		if (t3.isBefore(checkTime)) {
			System.out.println("출석");
		} else {
			System.out.println("지각");
		}
		
	}

	@Test
	void 출석테스트() {
		SiteUser user = this.userRepository.getById(1L);
		this.attendanceService.create(user, LocalDateTime.now());
		Attendance atd = this.attendanceService.getAttendance(1);
		System.out.println("id : " + atd.getId());
		System.out.println("status : " + atd.getStatus());
		System.out.println("Date : " + atd.getCheckinDate());
		System.out.println("Time : " + atd.getCheckinTime());
	}

	@Transactional
	@Test
	void 사람별_출석() {
		SiteUser user = this.userRepository.getById(1L);
		List<Attendance> my_atd = this.attendanceService.findByUser(user);
		for (Attendance atd : my_atd) {
			System.out.println("name : " + user.getName());
			System.out.println("status : " + atd.getStatus());
			System.out.println("Date : " + atd.getCheckinDate());
			System.out.println("Time : " + atd.getCheckinTime());
		}
	}
}

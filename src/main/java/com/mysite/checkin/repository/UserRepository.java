package com.mysite.checkin.repository;

import com.mysite.checkin.domain.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByuserid(String userid);
}

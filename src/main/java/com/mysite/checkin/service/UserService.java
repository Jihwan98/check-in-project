package com.mysite.checkin.service;

import com.mysite.checkin.DataNotFoundException;
import com.mysite.checkin.domain.SiteUser;
import com.mysite.checkin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String userid, String username, String password) {
        SiteUser user = new SiteUser();
        user.setUserid(userid);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String userid) {
        Optional<SiteUser> siteUser = this.userRepository.findByuserid(userid);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }
}

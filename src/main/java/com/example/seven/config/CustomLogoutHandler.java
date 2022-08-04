package com.example.seven.config;

import com.example.seven.domain.Status;
import com.example.seven.domain.User;
import com.example.seven.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutHandler implements LogoutHandler {

    private final UserRepository userRepository;

    public CustomLogoutHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            User user = userRepository.findByUsername(authentication.getName());
            if (user != null) {
                user.setStatus(Status.OFFLINE);
                userRepository.save(user);
            }
        }
    }
}

package com.example.seven.service;

import com.example.seven.domain.Role;
import com.example.seven.domain.Status;
import com.example.seven.domain.User;
import com.example.seven.dto.UserDto;
import com.example.seven.repository.UserRepository;
import com.example.seven.request.SingUpRequest;
import com.example.seven.request.UserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setStatus(Status.ONLINE);
        save(user);
        return user;
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public UserDto returnUserDto(String username){
        User user = findByUsername(username);
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .about(user.getAbout())
                .role(user.getRole())
                .status(user.getStatus())
                .notLock(user.getNotLock())
                .build();
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void createAccount(SingUpRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setStatus(Status.OFFLINE);
        user.setNotLock(true);
//        if (request.getUsername().equals("ADMIN")){
//            user.setRole(Role.ADMIN);
//            user.setPassword(passwordEncoder.encode("1"));
//        }
        save(user);
    }

    public void updateProfile(String username, UserRequest request){
        User user = findByUsername(username);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        save(user);
    }

    public void updateAbout(String username, UserRequest request){
        User user = findByUsername(username);
        user.setAbout(request.getAbout());
        save(user);
    }

    public void updateAdminProp(String username, UserRequest request){
        User user = findByUsername(username);
        user.setNotLock(request.getNotLock());
        user.setRole(Role.valueOf(request.getRole()));
        save(user);
    }
}

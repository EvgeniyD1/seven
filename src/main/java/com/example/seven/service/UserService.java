package com.example.seven.service;

import com.example.seven.domain.Role;
import com.example.seven.domain.Status;
import com.example.seven.domain.User;
import com.example.seven.repository.UserRepository;
import com.example.seven.request.UserUpdateRequest;
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

    public void save(User user){
        userRepository.save(user);
    }

    public void createAccount(String username, String password, String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setRole(Role.USER);
        user.setStatus(Status.OFFLINE);
        user.setNotLock(true);
        if (username.equals("ADMIN")){
            user.setRole(Role.ADMIN);
            user.setPassword(passwordEncoder.encode("1"));
        }
        save(user);
    }

    public void updateProfile(String username, UserUpdateRequest request){
        User user = findByUsername(username);
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        save(user);
    }

    public void updateAbout(String username, UserUpdateRequest request){
        User user = findByUsername(username);
        user.setAbout(request.getAbout());
        save(user);
    }

    public void updateAdminProp(String username, UserUpdateRequest request){
        User user = findByUsername(username);
        user.setNotLock(request.getNotLock());
        user.setRole(Role.valueOf(request.getRole()));
        save(user);
    }
}

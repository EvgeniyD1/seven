package com.example.seven.service;

import com.example.seven.domain.Role;
import com.example.seven.domain.Status;
import com.example.seven.domain.User;
import com.example.seven.dto.UserDto;
import com.example.seven.dto.UserMapper;
import com.example.seven.repository.UserRepository;
import com.example.seven.request.SingUpRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CloudService cloudService;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
        return user;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserDto returnUserDto(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return UserMapper.USER_MAPPER.userToUserDto(user);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void createAccount(SingUpRequest request) {
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
        userRepository.save(user);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void updateProfile(String username, UserDto request) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            userRepository.save(user);
        }
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void updateAbout(String username, UserDto request) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setAbout(request.getAbout());
            userRepository.save(user);
        }
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void updateAdminProp(String username, UserDto request) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setNotLock(request.getNotLock());
            user.setRole(Role.valueOf(request.getRole()));
            userRepository.save(user);
        }
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public String uploadImg(String username, MultipartFile img) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            String url = cloudService.getUrl(img);
            user.setImgUrl(url);
            userRepository.save(user);
            return url;
        }
        return null;
    }
}

package com.guessgame.guessgame.service;

import com.guessgame.guessgame.dto.UserDto;
import com.guessgame.guessgame.model.User;
import com.guessgame.guessgame.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(UserDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .fullName(dto.getFullName())
                .role(dto.getRole())
                .build();
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateFullName(Long id, String fullName) {
        return userRepository.findById(id).map(user -> {
            user.setFullName(fullName);
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

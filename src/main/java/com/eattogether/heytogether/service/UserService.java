package com.eattogether.heytogether.service;

import com.eattogether.heytogether.domain.User;
import com.eattogether.heytogether.domain.repository.UserRepository;
import com.eattogether.heytogether.service.assembler.UserAssembler;
import com.eattogether.heytogether.service.dto.LoginDto;
import com.eattogether.heytogether.service.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto loginUser(LoginDto loginDto) {
        User user = userRepository.findByUserId(loginDto.getUserId()).orElseThrow(IllegalArgumentException::new);

        if (isNotSamePassword(loginDto, user)) {
            throw new IllegalArgumentException();
        }

        return UserAssembler.toDto(user);
    }

    private boolean isNotSamePassword(final LoginDto loginDto, final User user) {
        return !user.checkPassword(loginDto.getPassword());
    }
}

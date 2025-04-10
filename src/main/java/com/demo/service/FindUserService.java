package com.demo.service;

import com.demo.DTO.responses.UserResponseDto;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.converter.UserConverter;
import com.demo.service.exception.NonExistingEmailException;
import com.demo.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindUserService {
    private final UserRepository userRepository;
    private final UserConverter converter;


    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(manager -> converter.createDtoFromUser(manager))
                .toList();
    }

    public UserResponseDto findUserByEmail(String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserResponseDto response = converter.createDtoFromUser(userOptional.get());
            return response;
        } else {
            throw new NotFoundException("User with email " + email + " not found");
        }
    }

    public List<User> findAllFullDetails() {
        return userRepository.findAll();
    }

    public boolean existsByEmail(String email) {
        try {
            userRepository.findByEmail(email)
                    .orElseThrow(() -> new NonExistingEmailException("User not found"));
            return true;
        } catch (NotFoundException e) {
            return false;
        }

    }


}
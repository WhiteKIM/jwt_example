package com.react.security.service;

import com.react.security.model.Member;
import com.react.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(Member member) {
        userRepository.save(member);
    }

    public List<Member> findAllMember() {
        return userRepository.findAll();
    }
}

package com.react.security.controller;

import com.react.security.model.Member;
import com.react.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final BCryptPasswordEncoder encoder;
    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody Member member) {
        member.setPassword(encoder.encode(member.getPassword()));
        userService.save(member);

        return "회원가입 완료";
    }

    @GetMapping("/user")
    public List<Member> findAll() {
        return userService.findAllMember();
    }
}

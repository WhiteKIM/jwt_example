package com.react.security.config.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.react.security.config.Auth.PrincipalDetails;
import com.react.security.model.Member;
import com.react.security.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {
        System.out.println("토큰 유효성 검사 진행");
        String jwtHeader = request.getHeader("Authorization");
        System.out.println(jwtHeader);
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            throw new RuntimeException("token is not Valid");
        }

        String token = request.getHeader("Authorization").replace("Bearer' ", "");
        String username = JWT
                .require(Algorithm.HMAC512("whitekim")).build().verify(token).getClaim("username").asString();

        if (username != null) {
            Member loginMember = userRepository.findByUsername(username);
            PrincipalDetails principalDetails = new PrincipalDetails(loginMember);

            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}

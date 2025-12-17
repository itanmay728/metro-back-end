package com.metro.metrobackend.security;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtService {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtService(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    // extract username (email)
    public String extractUsername(String token) {
        try {
            return jwtTokenUtil.getEmail(token);
        } catch (Exception e) {
            return null;
        }
    }

    // validate full token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String email = jwtTokenUtil.getEmail(token);

            return (email != null
                    && email.equals(userDetails.getUsername())
                    && jwtTokenUtil.validate(token));
        } catch (Exception e) {
            return false;
        }
    }
}

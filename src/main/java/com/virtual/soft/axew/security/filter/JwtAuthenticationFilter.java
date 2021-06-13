package com.virtual.soft.axew.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.soft.axew.dto.user.UserAuthDto;
import com.virtual.soft.axew.exception.JsonReaderException;
import com.virtual.soft.axew.security.jwt.JwtMaker;
import com.virtual.soft.axew.security.user.UserAuth;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String HEADER_AUTH = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final AuthenticationManager authenticationManager;
    private final JwtMaker jwtMaker;

    public JwtAuthenticationFilter (AuthenticationManager authenticationManager, JwtMaker jwtMaker) {
        this.authenticationManager = authenticationManager;
        this.jwtMaker = jwtMaker;
    }

    @Override
    public Authentication attemptAuthentication (HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserAuthDto userAuth = new ObjectMapper()
                    .readValue(request.getInputStream(), UserAuthDto.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userAuth.getEmail(),
                    userAuth.getPassword(),
                    new ArrayList<>());
            return authenticationManager.authenticate(authToken);
        } catch (IOException exception) {
            throw new JsonReaderException(exception.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication (HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName = ((UserAuth) authResult.getPrincipal()).getUsername();
        String token = jwtMaker.makeToken(userName);
        response.addHeader(HEADER_AUTH, TOKEN_PREFIX + token);
    }


}

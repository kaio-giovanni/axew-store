package com.virtual.soft.axew.security.filter;

import com.virtual.soft.axew.security.jwt.JwtAuth;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtAuth jwtAuth;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtAuth jwtAuth,
                                  UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtAuth = jwtAuth;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        UsernamePasswordAuthenticationToken userAuthorization = getAuthorization(request);
        if (userAuthorization != null && userAuthorization.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(userAuthorization);
        }

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthorization(HttpServletRequest request) {
        String authHeader = request.getHeader(jwtAuth.getHeaderAuth());

        if (jwtAuth.isValid(authHeader)) {
            String token = authHeader.split(" ")[1];
            String userName = jwtAuth.getUserName(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            return new UsernamePasswordAuthenticationToken(userDetails,
                    null,
                    userDetails.getAuthorities());
        }

        return null;
    }
}

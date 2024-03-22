package com.virtual.soft.axew.security.config;

import com.virtual.soft.axew.security.filter.JwtAuthenticationFilter;
import com.virtual.soft.axew.security.filter.JwtAuthorizationFilter;
import com.virtual.soft.axew.security.jwt.JwtAuth;
import com.virtual.soft.axew.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserAuthService userAuthService;
    private JwtAuth jwtAuth;

    @Autowired
    public WebSecurityConfig(UserAuthService userAuthService, JwtAuth jwtAuth) {
        this.userAuthService = userAuthService;
        this.jwtAuth = jwtAuth;
    }

    public WebSecurityConfig(boolean disableDefaults, UserAuthService userAuthService, JwtAuth jwtAuth) {
        super(disableDefaults);
        this.userAuthService = userAuthService;
        this.jwtAuth = jwtAuth;
    }

    private static final String[] GET_PUBLIC_MATCHERS = new String[]{
            "/category/**",
            "/product/**",
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui/**",
            "/openapi/**",
            "/swagger-ui-custom.html",
            "/webjars/**"
    };

    private static final String[] POST_PUBLIC_MATCHERS = new String[]{
            "/client/save",
            "/address/save"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, POST_PUBLIC_MATCHERS).permitAll()
                .antMatchers(HttpMethod.GET, GET_PUBLIC_MATCHERS).permitAll()
                .anyRequest()
                .authenticated();
        http
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtAuth))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),
                        jwtAuth,
                        userAuthService));

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthService).passwordEncoder(passwordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

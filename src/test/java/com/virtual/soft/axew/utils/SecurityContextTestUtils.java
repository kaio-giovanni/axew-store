package com.virtual.soft.axew.utils;

import com.virtual.soft.axew.entity.enums.RoleEnum;
import com.virtual.soft.axew.security.user.UserAuth;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.Set;

public class SecurityContextTestUtils {

    public static void fakeAuthentication (Long userId, Set<RoleEnum> roles) {

        UserAuth user = new UserAuth(userId, "fakeUser", "fakePass", roles);
        UsernamePasswordAuthenticationToken userAuthenticationToken = new UsernamePasswordAuthenticationToken(user,
                null, user.getAuthorities());

        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(userAuthenticationToken);
        SecurityContextHolder.setContext(securityContext);
    }

}


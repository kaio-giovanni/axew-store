package com.virtual.soft.axew.utils;

import com.virtual.soft.axew.security.user.UserAuth;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthUtils {

    private UserAuthUtils() {
        // Do nothing
    }

    public static UserAuth getAuthenticatedUser() {
        return (UserAuth) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}

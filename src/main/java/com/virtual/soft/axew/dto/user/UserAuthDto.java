package com.virtual.soft.axew.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserAuthDto {

    @Schema(example = "myemail@email.com")
    private String email;

    @Schema(example = "my_password")
    private String password;

    public UserAuthDto() {
        // Do nothing
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

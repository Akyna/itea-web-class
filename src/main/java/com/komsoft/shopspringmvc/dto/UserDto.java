package com.komsoft.shopspringmvc.dto;

import com.komsoft.shopspringmvc.model.UserModel;

public class UserDto {
    private long id;
    private String login;
    private String fullName;

    public UserDto(UserModel userModel) {
        this.id = userModel.getId();
        this.login = userModel.getLogin();
        this.fullName = userModel.getFullName();
    }

    public long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getFullName() {
        return this.fullName;
    }

}

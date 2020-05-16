package com.example.music2.bo;

import com.example.music2.DB.entity.User;
import com.example.music2.base.Response;
import lombok.Data;

@Data
public class LoginResponseBO extends Response {
    private static final long serialVersionUID = -7174620866502017737L;

    public User user;
}

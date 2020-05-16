package com.example.music2.vo;

import com.example.music2.DB.entity.User;
import com.example.music2.base.Response;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponseVO extends Response implements Serializable {
    private static final long serialVersionUID = 8807777805182826248L;

    public User user;

}

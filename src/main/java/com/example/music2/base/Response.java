package com.example.music2.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private static final long serialVersionUID = -6480414705113442345L;
    public String message;
    public String code;
}

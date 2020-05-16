package com.example.music2.entity;

import com.example.music2.DB.entity.Album;
import lombok.Data;

import java.io.Serializable;

@Data
public class ListenCounts implements Serializable {

    private static final long serialVersionUID = 5002029377516393418L;

    private String listenCounts;

    private Album album;
}

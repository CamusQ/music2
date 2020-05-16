package com.example.music2.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class AudioItem implements Serializable {

    private static final long serialVersionUID = 3226010889231963487L;

    private String headerUrl;
    private String nickName;
    private String descContent;
    private String titleImage;
    private String songName;

}

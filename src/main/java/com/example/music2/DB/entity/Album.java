package com.example.music2.DB.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Album)实体类
 *
 * @author camus_java
 * @since 2020-04-28 18:57:00
 */
@Data
public class Album implements Serializable {
    private static final long serialVersionUID = 116423553504560762L;
    
    private Integer id;
    
    private String producerName;
    
    private String producerNickname;
    
    private String albumCoverUrl;
    
    private String mediaLrcUrl;
    
    private String mediaUrl;
    
    private String albumName;

    private String headerIcon;

    private String contentDesc;


}
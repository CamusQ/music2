package com.example.music2.DB.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author camus_java
 * @since 2020-04-28 21:22:57
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 989177345201856740L;
    
    private Integer id;
    
    private String name;
    
    private String nickName;
    
    private String sex;

    private String age;

    private Date registerData;

    private String collectList;
    
    private Integer isSinger;
    
    private String headerImageUrl;
    
    private String albumName;

    private Date uploadDate;
    
    private Integer ispassed;

    private String recentlyListen;

    private String password;

}
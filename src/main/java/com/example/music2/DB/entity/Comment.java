package com.example.music2.DB.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Comment)实体类
 *
 * @author camus_java
 * @since 2020-05-10 09:37:05
 */
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 955545980270503336L;

    /*评论id*/
    private Integer id;

    /*要评论的专辑id*/
    private Integer replyAlbumId;

    /*待回复的评论id*/
    private Integer replyCommId;

    /*评论时间*/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date commTime;

    /*评论的用户名称*/
    private String commName;

    /*评论内容*/
    private String commContent;

    private String headerImageUrl;


}
package com.example.music2.DB.controller;

import com.alibaba.fastjson.JSON;
import com.example.music2.DB.entity.Comment;
import com.example.music2.DB.service.CommentService;
import com.example.music2.base.Const;
import com.example.music2.base.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * (Comment)表控制层
 *
 * @author camus_java
 * @since 2020-05-10 09:37:05
 */
@RestController
@RequestMapping("comment")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Comment selectOne(Integer id) {

        return this.commentService.queryById(id);
    }


    /**
     * 通过专辑id查询专辑下的所有评论
     *
     * @param albumID 专辑id
     * @return 评论集合
     */
    @GetMapping("getAlbumComments")
    public ArrayList<Comment> getAlbumComments(@RequestParam Integer albumID) {
        ArrayList<Comment> comments = commentService.queryByAlbumID(albumID);
        log.info("获取评论出参：{}", JSON.toJSONString(comments));

        return comments;
    }


    /**
     * 添加评论
     *
     * @param comment 评论实例
     * @return 状态
     */
    @PostMapping("addComment")
    public Response addComment(@RequestBody Comment comment) {
        log.info("添加评论入参：{}", JSON.toJSONString(comment));

        Response bo = new Response();
        Comment com = commentService.insert(comment);

        if (com.getId() != null) {
            bo.code = Const.CODE_SUCCESS;
            bo.message = "评论成功";
            return bo;
        }
        bo.code = Const.CODE_FAILED;
        bo.message = "评论失败";
        return bo;
    }

    @GetMapping("getComm")
    public ArrayList<Comment> getSlaveComms(@RequestParam Integer replyCommId, @RequestParam Integer commId) {
        log.info("获取子评论入参:{}", replyCommId +" --- "+ commId);
        Comment comment = new Comment();
//        comment.setReplyCommId(replyCommId);
        comment.setId(commId);
        ArrayList<Comment> comments = commentService.querySlaveComment(comment);

        log.info("子评论出参：{}",JSON.toJSONString(comments));
        return comments;
    }


}
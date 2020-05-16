package com.example.music2.DB.service;

import com.example.music2.DB.entity.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * (Comment)表服务接口
 *
 * @author camus_java
 * @since 2020-05-10 09:37:05
 */
public interface CommentService {

    /**
     * 查询子评论
     *
     * @param comment 实例
     * @return 评论集合
     */
    ArrayList<Comment> querySlaveComment(Comment comment);


    /**
     * 通过实例查询
     *
     * @param comment 评论实例
     * @return 评论集合
     */
    ArrayList<Comment> queryAll(Comment comment);


    ArrayList<Comment> queryByAlbumID(Integer albumID);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    Comment update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
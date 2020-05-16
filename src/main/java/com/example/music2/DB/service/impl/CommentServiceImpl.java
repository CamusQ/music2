package com.example.music2.DB.service.impl;

import com.example.music2.DB.entity.Comment;
import com.example.music2.DB.dao.CommentDao;
import com.example.music2.DB.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Comment)表服务实现类
 *
 * @author camus_java
 * @since 2020-05-10 09:37:05
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;


    /**
     * 通过实例查询子评论
     *
     * @param comment 实例
     * @return 评论集合
     */
    @Override
    public ArrayList<Comment> querySlaveComment(Comment comment) {
        return commentDao.querySlaveComment(comment);
    }

    /**
     * 通过实例查询
     *
     * @param comment 评论实例
     * @return 评论集合
     */
    @Override
    public ArrayList<Comment> queryAll(Comment comment) {
        return commentDao.queryAll(comment);
    }

    @Override
    public ArrayList<Comment> queryByAlbumID(Integer albumID) {
        return commentDao.queryByAlbumID(albumID);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Comment queryById(Integer id) {
        return this.commentDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Comment> queryAllByLimit(int offset, int limit) {
        return this.commentDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment insert(Comment comment) {
        this.commentDao.insert(comment);
        return comment;
    }

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 实例对象
     */
    @Override
    public Comment update(Comment comment) {
        this.commentDao.update(comment);
        return this.queryById(comment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.commentDao.deleteById(id) > 0;
    }
}
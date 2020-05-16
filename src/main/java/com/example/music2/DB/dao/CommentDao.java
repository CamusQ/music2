package com.example.music2.DB.dao;

import com.example.music2.DB.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * (Comment)表数据库访问层
 *
 * @author camus_java
 * @since 2020-05-10 09:37:05
 */
public interface CommentDao {


    ArrayList<Comment> querySlaveComment(Comment comment );

    /**
     * 通过专辑id查找
     *
     * @param albumID 专辑id
     * @return 评论集合
     */
    ArrayList<Comment> queryByAlbumID(Integer albumID);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Comment> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param comment 实例对象
     * @return 对象列表
     */
    ArrayList<Comment> queryAll(Comment comment);

    /**
     * 新增数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);

    /**
     * 修改数据
     *
     * @param comment 实例对象
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}
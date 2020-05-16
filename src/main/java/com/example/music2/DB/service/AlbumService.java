package com.example.music2.DB.service;

import com.example.music2.DB.entity.Album;
import com.example.music2.base.Response;

import java.util.List;

/**
 * (Album)表服务接口
 *
 * @author camus_java
 * @since 2020-04-28 18:57:00
 */
public interface AlbumService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Album queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Album> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param album 专辑实例
     * @return 响应结果
     */
    Response insert(Album album);

    /**
     * 修改数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    Album update(Album album);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);


    /**
     * 通过对象查询
     *
     * @param album
     * @return
     */
    List<Album> queryAll(Album album);

}
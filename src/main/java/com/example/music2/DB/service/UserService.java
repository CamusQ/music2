package com.example.music2.DB.service;

import com.example.music2.DB.entity.Album;
import com.example.music2.DB.entity.User;
import com.example.music2.bo.LoginResponseBO;
import com.example.music2.entity.ListenCounts;

import java.util.ArrayList;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author camus_java
 * @since 2020-04-28 21:22:57
 */
public interface UserService {

    /**
     * 通过实例查找
     *
     * @param user 用户实例
     * @return 结果集
     */
    List<User> queryAll(User user);


    /**
     * 通过name 查询数据
     *
     * @param name 用户名
     * @return 用户实例
     */
    List<User> queryByName(String name);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    LoginResponseBO insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过用户昵称 查询收藏夹
     * @return
     */
    ArrayList<Album> getCollectByNickName(String nickName);


    /**
     * 更新收藏夹
     * @param user 用户实例
     */
    void updateUserCollect(User user);

    /**
     * 通过id 获取最近常听列表
     * @param id
     * @return
     */
    ArrayList<ListenCounts> getRecentlyListen(Integer id);

}
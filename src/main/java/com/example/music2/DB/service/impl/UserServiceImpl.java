package com.example.music2.DB.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.music2.DB.dao.AlbumDao;
import com.example.music2.DB.dao.UserDao;
import com.example.music2.DB.entity.Album;
import com.example.music2.DB.entity.User;
import com.example.music2.DB.service.UserService;
import com.example.music2.base.Const;
import com.example.music2.bo.LoginResponseBO;
import com.example.music2.entity.ListenCounts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author camus_java
 * @since 2020-04-28 21:22:57
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Resource
    private AlbumDao albumDao;


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @Override
    public List<User> queryAll(User user) {
        return userDao.queryAll(user);
    }

    /**
     * 通过name 查询数据
     *
     * @param name 用户名
     * @return 用户实例
     */
    @Override
    public List<User> queryByName(String name) {
        User user = new User();
        user.setName(name);
        List<User> userList = userDao.queryAll(user);
        return userList;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return LoginResponseBO
     */
    @Override
    public LoginResponseBO insert(User user) {
        log.info("新增用户信息：{}",user);
        user.setRegisterData(new Date());

        LoginResponseBO loginResponseBO = new LoginResponseBO();
        try{
            userDao.insert(user);
            log.info("插入成功后的用户信息：{}",JSON.toJSONString(user));
            loginResponseBO.message = "注册成功！";
            loginResponseBO.code = Const.CODE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            loginResponseBO.message = "请检查用户名密码是否正确";
            loginResponseBO.code = Const.CODE_FAILED;
            return loginResponseBO;
        }

        User user1 = new User();
        user1.setName(user.getName());
        List<User> userList = userDao.queryAll(user1);
        loginResponseBO.user = userList.get(0);


        return loginResponseBO;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }


    /**
     * 通过用户昵称 查询收藏夹
     *
     * @param nickName
     * @return
     */
    @Override
    public ArrayList<Album> getCollectByNickName(String nickName) {
        ArrayList<Album> albums = new ArrayList<>();
        String collectList = userDao.queryByNickName(nickName);

        // 下标 0 是歌手名 ，1 是专辑名
        String[] strs = collectList.split("[,，]");

        for (String str : strs) {
            String[] split = str.split("[:]");
            Album album = new Album();
            album.setProducerName(split[0].replaceAll(" ", ""));
            album.setAlbumName(split[1].replaceAll(" ", ""));
            Album a = null;
            try {
                a = albumDao.queryAll(album).get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            albums.add(a);
        }

        log.info("收藏夹：{}", albums.toString());
        return albums;
    }

    /**
     * 更新收藏夹
     *
     * @param user 对象实例
     */
    @Override
    public void updateUserCollect(User user) {

        /**
         * 3号 任务之一，将这里的id集合 和 歌手:专辑名 映射
         */


        String collectList = user.getCollectList();
        collectList = collectList.substring(1, collectList.indexOf("]"));
        collectList = collectList.replaceAll(" ", "");
        if (collectList.length() < 1) {
            user.setCollectList("");
            userDao.update(user);
            return;
        }
        String[] split = collectList.split(",");
        int id;
        List<String> collect = new ArrayList<String>();
        for (String albumId : split) {
            id = Integer.parseInt(albumId);
            Album album = albumDao.queryById(id);
            collect.add(album.getProducerName() + ":" + album.getAlbumName());
        }

        String tmp = collect.toString();
        tmp = tmp.substring(1, tmp.indexOf("]"));
        user.setCollectList(tmp);
        log.info("更新收藏夹的数据为：{}", tmp);
//        collectList.
        userDao.update(user);
    }


    /**
     * 获取用户最近常听列表
     * @param id 用户id
     */
    @Override
    public ArrayList<ListenCounts> getRecentlyListen(Integer id) {

        User user = userDao.queryById(id);
        String recent = user.getRecentlyListen();
        ArrayList<ListenCounts> albumList = new ArrayList<>();


        if(recent == null || recent.length() == 0){
            return albumList;
        }

        String[] split = recent.split(",");
        for (String s : split) {
            String albumId = s.substring(0,s.indexOf("="));
            String counts = s.substring(s.indexOf("=") + 1);
            Album album = albumDao.queryById(Integer.parseInt(albumId));
            ListenCounts inst = new ListenCounts();
            inst.setAlbum(album);
            inst.setListenCounts(counts);
            albumList.add(inst);
        }

        log.info("最近常听列表：{}", albumList);
        return albumList;
    }
}
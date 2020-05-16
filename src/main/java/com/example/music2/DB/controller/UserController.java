package com.example.music2.DB.controller;

import com.alibaba.fastjson.JSON;
import com.example.music2.DB.entity.Album;
import com.example.music2.DB.entity.User;
import com.example.music2.DB.service.UserService;
import com.example.music2.base.Const;
import com.example.music2.bo.LoginResponseBO;
import com.example.music2.entity.ListenCounts;
import com.example.music2.vo.LoginResponseVO;
import com.example.music2.vo.UpdateUserInfoResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author camus_java
 * @since 2020-04-28 21:22:57
 */
@RestController
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    @PostMapping("login")
    public LoginResponseVO login(@RequestBody User user) {

        log.info("登录 入参：{}", JSON.toJSONString(user));
        List<User> users = userService.queryByName(user.getName());
        LoginResponseVO loginResponseVO = new LoginResponseVO();
        if (users.size() > 0) {
            if (users.get(0).getPassword().equals(user.getPassword())) {
                loginResponseVO.message = "登录成功！";
                loginResponseVO.code = Const.CODE_SUCCESS;
                loginResponseVO.user = users.get(0);
                return loginResponseVO;
            }
            loginResponseVO.message = "用户名已被占用";
            loginResponseVO.code = Const.CODE_FAILED;
            return loginResponseVO;
        }

        LoginResponseVO vo = new LoginResponseVO();
        LoginResponseBO bo = userService.insert(user);

        BeanUtils.copyProperties(bo, vo);
        log.info("登录返回值：{}", JSON.toJSONString(vo));
        return vo;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    /**
     * 通过用户昵称 查询收藏夹
     *
     * @param nickName 用户昵称
     * @return 收藏夹列表
     */
    @GetMapping("getUserCollect")
    public ArrayList<Album> getUserCollect(@RequestParam String nickName) {
        log.info("nickName:{}", nickName);
        return userService.getCollectByNickName(nickName);
    }


    /**
     * 更新用户信息
     *
     * @param user 用户实例
     */
    @PostMapping("updateUserInfo")
    public UpdateUserInfoResponseVO updateUserNickname(@RequestBody User user) {
        log.info("更新用户信息 入参：{}", JSON.toJSONString(user));
        User tmpUser = new User();
        UpdateUserInfoResponseVO vo = new UpdateUserInfoResponseVO();

        if (user.getNickName() != null && user.getNickName().isEmpty()) {
            tmpUser.setNickName(user.getNickName());
            List<User> userList = userService.queryAll(tmpUser);
            if (userList.size() > 0) {
                vo.code = Const.CODE_FAILED;
                vo.message = "此昵称已被占用";
                return vo;
            }
        }

        userService.update(user);
        vo.code = Const.CODE_SUCCESS;
        vo.message = "修改成功";
        return vo;
    }


    /**
     * 更新收藏夹
     *
     * @param user 变更后的收藏专辑id集合
     */
    @PostMapping("updateUserCollect")
    public void updateUserCollect(@RequestBody User user) {
        log.info("收到的用户信息：{}", user);
        userService.updateUserCollect(user);
    }


    /**
     * 获取用户最近常听列表
     *
     * @param id 用户id
     */
    @GetMapping("getUserRecentlyListen")
    public ArrayList<ListenCounts> getUserRecentlyListen(@RequestParam Integer id) {
        log.info("收到的用户id：{}", id);

        return userService.getRecentlyListen(id);
    }

    /**
     * 更新最近常听列表
     *
     * @param user 用户实例
     */
    @PostMapping("updateUserRecentlyListen")
    public void updateUserRecentlyListen(@RequestBody User user) {
        log.info("收到的用户更新的最近常听信息：{}", user);
        String listen = user.getRecentlyListen();
        listen = listen.replaceAll(" ", "");
        user.setRecentlyListen(listen);
        User update = userService.update(user);
        log.info("更新最近常听 结果：{}", JSON.toJSONString(update));
    }


}
package com.example.music2.DB.controller;

import com.example.music2.DB.entity.Album;
import com.example.music2.DB.entity.User;
import com.example.music2.DB.service.AlbumService;
import com.example.music2.DB.service.UserService;
import com.example.music2.base.Const;
import com.example.music2.base.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Album)表控制层
 *
 * @author camus_java
 * @since 2020-04-28 18:57:00
 */
@RestController
@RequestMapping("album")
public class AlbumController {

    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    /**
     * 服务对象
     */
    @Resource
    private AlbumService albumService;

    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("selectOne")
    public Album selectOne(@RequestParam("id") Integer id) {
        log.info("入参 albumID：{}", id);
        Album album = this.albumService.queryById(id);
        log.info(album.toString());
        return album;
    }


    /**
     * 通过区间获取列表
     *
     * @param offset 起点
     * @param limit  终点
     * @return 专辑集合
     */
    //    url：key=value&key=value
    /* http://localhost:8080/album/getAudioList?offset=0&limit=20 */
    @RequestMapping("getAudioList")
    public List<Album> getAudioByLimit(@RequestParam int offset, @RequestParam int limit) {
        List<Album> albumList = albumService.queryAllByLimit(offset, limit);
        log.info("获取音频列表 ：{}", albumList.toString());
        return albumList;
    }


    /**
     * 添加专辑
     *
     * @param id        用户id
     * @param albumDesc 专辑描述
     * @return 响应状态
     */
    @RequestMapping("addAlbum")
    public Response addAlbum(@RequestParam Integer id, @RequestParam String albumDesc) {
        /*传入用户 id 在用户表中查找 -name，nickName，headerURL-*/

        log.info("const 中的 path：{}", Const.PATH);


        String path = Const.PATH;
        User user = userService.queryById(id);
        Album album = new Album();
        String[] splits = path.split("====");
        Const.PATH = "";
        String albumName = splits[0].substring(splits[0].indexOf("-") + 1, splits[0].lastIndexOf(".")).trim();

        album.setAlbumName(albumName);
        album.setProducerNickname(user.getNickName());
        album.setMediaUrl(Const.ALBUM_PATH + splits[0].substring(splits[0].lastIndexOf("/") + 1));
        album.setMediaLrcUrl(Const.ALBUM_PATH + splits[1].substring(splits[1].lastIndexOf("/") + 1));
        album.setHeaderIcon(user.getHeaderImageUrl());
        album.setContentDesc(albumDesc);
        album.setAlbumCoverUrl(Const.COVER_PATH + splits[2].substring(splits[2].lastIndexOf("/") + 1));
        album.setProducerName(user.getName());

        log.info("const 中的 path：{}", Const.PATH);

        return albumService.insert(album);
    }


    /**
     * 在数据库中检查是否已存在
     *
     * @param albumName 专辑名
     * @return
     */
    @RequestMapping("checkAlbum")
    public Response checkAlbum(@RequestParam String albumName) {
        Response bo = new Response();
        Album album = new Album();
        album.setAlbumName(albumName);
        List<Album> albumList = albumService.queryAll(album);
        if (albumList.size() > 0) {
            bo.code = Const.CODE_FAILED;
            bo.message = Const.MSG_FAILED;
            return bo;
        }
        bo.code = Const.CODE_SUCCESS;
        bo.message = Const.MSG_SUCCESS;
        return bo;
    }

}
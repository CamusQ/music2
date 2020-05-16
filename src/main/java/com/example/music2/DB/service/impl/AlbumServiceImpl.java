package com.example.music2.DB.service.impl;

import com.example.music2.DB.dao.AlbumDao;
import com.example.music2.DB.dao.UserDao;
import com.example.music2.DB.entity.Album;
import com.example.music2.DB.service.AlbumService;
import com.example.music2.base.Const;
import com.example.music2.base.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Album)表服务实现类
 *
 * @author camus_java
 * @since 2020-04-28 18:57:00
 */
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

    @Resource
    private AlbumDao albumDao;

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Album queryById(Integer id) {
        return this.albumDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Album> queryAllByLimit(int offset, int limit) {
        return this.albumDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param album 专辑实例
     * @return 响应结果
     */
    @Override
    public Response insert(Album album) {
        Response bo = new Response();
        try {
            albumDao.insert(album);
            bo.code = Const.CODE_SUCCESS;
            bo.message = "新增专辑成功";
        } catch (Exception e) {
            e.printStackTrace();
            bo.code = Const.CODE_FAILED;
            bo.message = "新增专辑失败";
        }
        return bo;
    }

    /**
     * 修改数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    @Override
    public Album update(Album album) {
        this.albumDao.update(album);
        return this.queryById(album.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.albumDao.deleteById(id) > 0;
    }


    /**
     * 通过对现象查询
     *
     * @param album 实例对象
     * @return 对象列表
     */
    @Override
    public List<Album> queryAll(Album album) {
        return albumDao.queryAll(album);
    }
}
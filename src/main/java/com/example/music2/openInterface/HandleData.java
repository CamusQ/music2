package com.example.music2.openInterface;

import com.alibaba.fastjson.JSON;
import com.example.music2.base.Response;
import com.example.music2.service.AudioFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("handleData")
public class HandleData {
    private static final Logger log = LoggerFactory.getLogger(AudioFileService.class);

    @Autowired
    AudioFileService audioFile;

    @RequestMapping("pushDesc")
    public void pushAlbumDesc(@RequestParam String albumDesc) {

    }

    @RequestMapping("upload")
    public Response saveFile(@RequestParam("music_key") MultipartFile file) {
        return audioFile.upload(file);
    }

    @RequestMapping("download")
    public void downloadFile(@RequestParam String fileName, HttpServletResponse response) {
        /*这里指定下载音乐，后面可能改*/
        Response bo = audioFile.download(response, fileName);
        log.info("调用下载文件服务：{}", JSON.toJSONString(bo));
    }


}

package com.example.music2.service;

import com.example.music2.base.Const;
import com.example.music2.base.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@Service("audioFile")
public class AudioFileService {
    private static final Logger log = LoggerFactory.getLogger(AudioFileService.class);
    public static final Integer DOWNLOAD_MUSIC = 3;
    public static final Integer DOWNLOAD_LRC = 2;
    public static final Integer DOWNLOAD_IMAGE = 1;


    public Response upload(MultipartFile file) {
        Response response = new Response();
        try {
            if (file.isEmpty()) {
                response.code = Const.CODE_FAILED;
                response.message = Const.MSG_FAILED;
                return response;
            }

            // 获取文件名
            String fileName = URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
            log.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            assert fileName != null;
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件的后缀名为：" + suffixName);
            // 设置文件存储路径
            String filePath = "D:/musicDataSource/";
            if (suffixName.contains("mp3") || suffixName.contains("lrc")) {
                filePath += "music/";
            }
            if (suffixName.contains("jpg") || suffixName.contains("jpeg")) {
                filePath += "image/";
            }
            String path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            response.code = Const.CODE_SUCCESS;
            response.message = Const.MSG_SUCCESS;

            Const.PATH += path + "====";


            return response;
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        response.code = Const.CODE_FAILED;
        response.message = Const.MSG_FAILED;
        return response;
    }


    public Response download(HttpServletResponse response, String fileName) {
        Response bo = new Response();
        if (fileName == null) {
            bo.code = Const.CODE_FAILED;
            bo.message = "文件名为空";
            return bo;
        }
        //设置文件路径
        String pathName = Const.MUSIC_PATH;
//            if (flag.equals(DOWNLOAD_IMAGE)) {
//                pathName += "image/";
//            }
//            if (flag.equals(DOWNLOAD_MUSIC)) {
//                pathName += "music/";
//            }
        pathName += fileName;
        File file = new File(pathName);

        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                bo.code = Const.CODE_SUCCESS;
                bo.message = "下载成功";
                return bo;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        bo.code = Const.CODE_FAILED;
        bo.message = "下载失败";
        return bo;
    }

}

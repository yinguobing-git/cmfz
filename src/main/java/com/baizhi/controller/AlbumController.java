package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @ResponseBody
    @RequestMapping("findAll")
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> albums = albumService.findAll(page, rows);
        return albums;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map<String, Object> edit(String oper, Album album) {
        Map<String, Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)) {
                String id = albumService.add(album);
                map.put("message", id);
            }
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile coverImg, String id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
//            文件上传
//           cover.transferTo(new File(request.getServletContext().getRealPath("/album/img"),cover.getOriginalFilename()));
//            修改album对象中cover属性
            //1.获取文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/album/img");
            //2.获取文件夹
            File file = new File(realPath);
            //3.创建文件夹
            if (!file.exists()) {
                file.mkdirs();
            }
            //4.获取上传文件名
            String filename = coverImg.getOriginalFilename();
            //5.文件上传
            coverImg.transferTo(new File(realPath, filename));
            Album album = new Album();
            album.setId(id);
            album.setCoverImg(coverImg.getOriginalFilename());
            albumService.edit(album);
            map.put("status", true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", false);
        }
        return map;
    }

}

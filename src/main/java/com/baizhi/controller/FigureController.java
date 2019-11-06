package com.baizhi.controller;

import com.baizhi.entity.Figure;
import com.baizhi.service.FigureService;
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
@RequestMapping("figure")
public class FigureController {
    @Autowired
    private FigureService figureService;

    @ResponseBody
    @RequestMapping("findAll")
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = figureService.findAll(page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map<String, Object> edit(String oper, Figure figure, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("////////////////////" + figure + "----------");
        try {
            if ("add".equals(oper)) {
                //添加轮播图
                String id = figureService.add(figure);
                map.put("message", id);
            } else if ("edit".equals(oper)) {
                //修改轮播图
                String id = figureService.update(figure);
                map.put("message", id);
            } else if ("del".equals(oper)) {
                //删除轮播图
                figureService.delete(figure.getId(), request);
            }
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("upload")
    public Map<String, Object> upload(MultipartFile imgPath, String id, HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        try {
            //文件上传
            imgPath.transferTo(new File(request.getServletContext().getRealPath("/figure/img"), imgPath.getOriginalFilename()));
            //修改banner对象中cover属性
            Figure figure = new Figure();
            figure.setId(id);
            figure.setImgPath(imgPath.getOriginalFilename());
            System.out.println(figure + "+-*/-+7849---");
            figureService.update(figure);
            System.out.println(figure);
            map.put("status", true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }
}

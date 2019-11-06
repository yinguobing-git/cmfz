package com.baizhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.baizhi.entity.Star;
import com.baizhi.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("star")
public class StarController {
    @Autowired
    private StarService starService;

    @ResponseBody
    @RequestMapping("findAll")

    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        map = starService.findAll(page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("edit")
    public Map<String, Object> edit(String oper, Star star, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)) {
                //添加明星方法
                star.setId(UUID.randomUUID().toString());
                String id = starService.add(star);
                map.put("message", id);
            } else if ("edit".equals(oper)) {
                //编辑明星方法
                starService.update(star);
            } else if ("del".equals(oper)) {
                //删除明星方法
                starService.delete(star.getId(), request);
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
    public Map<String, Object> upload(MultipartFile photo, String id, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //文件上传
            photo.transferTo(new File(request.getServletContext().getRealPath("/star/img"), photo.getOriginalFilename()));
            //修改star中的phone属性
            Star star = new Star();
            star.setId(id);
            star.setPhoto(photo.getOriginalFilename());
            starService.update(star);
            map.put("status", true);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("selectAll")
    public void selectAll(HttpServletResponse response) throws IOException {
        List<Star> list = starService.selectAll();
        String str = "<select>";
        for (Star star : list) {
            str += "<option value=" + star.getId() + ">" + star.getNickname() + "</option>";
        }
        str += "</select>";
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(str);
    }

    @ResponseBody
    @RequestMapping("select")
    public Star select(Star s, HttpServletResponse response) {
        Star star = starService.select(s.getId());
        JSONObject.toJSONString(star);
        return null;
    }
}

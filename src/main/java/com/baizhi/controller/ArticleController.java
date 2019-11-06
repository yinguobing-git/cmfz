package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping("findAll")
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = articleService.findAll(page, rows);
        return map;
    }

    @ResponseBody
    @RequestMapping("edit")
    //三个参数   1.操作相关属性  2.文章对象
    public Map<String, Object> edit(String oper, Article article, String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if ("add".equals(oper)) {
                String id1 = articleService.add(article);
                GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-e891702436bc4f33b60b17739844b6b2");
                goEasy.publish("channel001", "新文章" + article.getTitle() + "上线");
                map.put("message", id1);
            } else if ("del".equals(oper)) {
                System.out.println(article.getId() + "*********************");
                System.out.println(article + "--------------------------");
                articleService.delete(id);
            } else if ("update".equals(oper)) {
                articleService.update(article);
            }
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
        }
        return map;
    }

    @RequestMapping("upload")
    public void upload() {

    }
}

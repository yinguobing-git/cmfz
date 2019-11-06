package com.baizhi.service;

import com.baizhi.entity.Article;

import java.util.Map;

public interface ArticleService {
    //1.查询所有文章
    Map<String, Object> findAll(Integer page, Integer rows);

    //2.删除文章
    void delete(String id);

    //3.添加文章
    String add(Article article);

    //4.修改文章
    void update(Article article);
}

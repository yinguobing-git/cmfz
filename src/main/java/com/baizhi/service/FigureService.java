package com.baizhi.service;

import com.baizhi.entity.Figure;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface FigureService {
    //分页查询轮播图
    // 1.起始条数  2.每页条数
    Map<String, Object> findAll(Integer page, Integer rows);

    //添加轮播图
    String add(Figure figure);

    //修改轮播图
    String update(Figure figure);

    //删除轮播图
    void delete(String id, HttpServletRequest request);
}

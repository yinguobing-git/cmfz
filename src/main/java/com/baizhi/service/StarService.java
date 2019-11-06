package com.baizhi.service;

import com.baizhi.entity.Star;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface StarService {
    //1.查询是由于明星
    Map<String, Object> findAll(Integer page, Integer rows);

    //2.添加明星
    String add(Star star);

    //3.删除明星
    void delete(String id, HttpServletRequest request);

    //4.修改明星
    void update(Star star);

    //5.查询所有不带分页
    List<Star> selectAll();

    //根据id查明星
    Star select(String id);
}

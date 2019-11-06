package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.Map;

public interface AlbumService {
    //1.查询所有专辑
    Map<String, Object> findAll(Integer page, Integer rows);

    //2.添加一个专辑
    String add(Album album);

    //3.修改
    public void edit(Album album);

    //4.根据id查询一个专辑
    Album selectOne(String id);
}

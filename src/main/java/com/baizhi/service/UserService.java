package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, Object> findUsersByStarId(Integer page, Integer rows, String starId);

    Map<String, Object> findAllUsers(Integer page, Integer rows);

    List<User> selectAll();

    Map<String, List<Integer>> findByMonthAndSex();
}

package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> findUsersByStarId(Integer page, Integer rows, String starId) {
        Map<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        User user = new User();
        user.setStar_id(starId);
        List<User> users = userDao.selectByRowBounds(user, rowBounds);
        int count = userDao.selectCount(user);
        map.put("page", page);
        map.put("rows", users);
        map.put("total", count % page == 0 ? count / page : count / page + 1);
        map.put("records", count);
        return map;
    }

    @Override
    public Map<String, Object> findAllUsers(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        User user = new User();
        List<User> users = userDao.selectByRowBounds(user, rowBounds);
        int count = userDao.selectCount(user);
        map.put("page", page);
        map.put("rows", users);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);
        map.put("records", count);
        return map;
    }

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

    @Override
    public Map<String, List<Integer>> findByMonthAndSex() {
        Map<String, List<Integer>> hashMap = new HashMap<String, List<Integer>>();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Integer count = userDao.selectByMonthAndSex(i, "男");
            list.add(count);
        }
        hashMap.put("nan", list);
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Integer count = userDao.selectByMonthAndSex(i, "女");
            list1.add(count);
        }
        hashMap.put("nv", list1);
        return hashMap;
    }
}

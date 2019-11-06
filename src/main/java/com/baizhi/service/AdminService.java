package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface AdminService {
    Map<String, Object> login(Admin admin, String inputCode, HttpServletRequest request);

    String regist(Admin admin);

    List<Admin> selectAdminsByPhone(Admin admin);
}

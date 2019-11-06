package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String, Object> login(Admin admin, String inputCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //1.判断验证码是否正确
        String securityCode = (String) request.getSession().getAttribute("securityCode");
        if (!securityCode.equals(inputCode)) {
            if (!securityCode.equals(inputCode)) {
                map.put("status", false);
                map.put("message", "对不起，你输入的验证码" + inputCode + "错误！");
            }
        } else {
            Admin a = new Admin();
            a.setName(admin.getName());
            Admin admin1 = adminDao.selectOne(a);
            if (admin1 == null) {
                map.put("status", false);
                map.put("message", "对不起，你输入的用户名" + admin.getName() + "不存在！");
            } else if (!admin1.getPassword().equals(admin.getPassword())) {
                map.put("status", false);
                map.put("message", "对不起，你输入的密码" + admin.getPassword() + "错误！");
            } else {
                map.put("status", true);
                request.getSession().setAttribute("login", admin1);
            }
        }
        return map;
    }

    @Override
    public String regist(Admin admin) {
        admin.setId(UUID.randomUUID().toString());
        adminDao.insert(admin);
        return admin.getId();
    }

    @Override
    public List<Admin> selectAdminsByPhone(Admin admin) {
        Admin a = new Admin();
        a.setPhone(admin.getPhone());
        List<Admin> admins = adminDao.select(a);
        return admins;
    }
}

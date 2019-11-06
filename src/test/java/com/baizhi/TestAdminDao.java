package com.baizhi;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestAdminDao {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AdminService adminService;

    @Test
    void testLoads() {
        List<Admin> list = adminDao.selectAll();
        list.forEach(admin -> System.out.println(admin));
    }
    /*@Test
    void  test1(){
        Admin admin=new Admin();
        admin.setName("zhangsan");
        admin.setPassword("111111");
        adminService.login(admin);
    }*/
}

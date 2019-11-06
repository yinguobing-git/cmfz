package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("findUsersByStarId")
    public Map<String, Object> findUsersByStarId(Integer page, Integer rows, String starId) {
        Map<String, Object> map = userService.findUsersByStarId(page, rows, starId);
        return map;
    }

    @ResponseBody
    @RequestMapping("findAllUsers")
    public Map<String, Object> findAllUsers(Integer page, Integer rows) {
        Map<String, Object> map = userService.findAllUsers(page, rows);
        return map;
    }

    @RequestMapping("export")
    public void export(HttpServletRequest request, HttpServletResponse resp) {
        List<User> users = userService.selectAll();
        String realPath = request.getSession().getServletContext().getRealPath("/user/img");
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setPhoto(realPath + "/" + users.get(i).getPhoto());
        }
        //将list集合以excel表格的形式导出
        ExportParams exportParams = new ExportParams("所有用户", "用户表");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, users);
        String fileName = "用户报表(" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ").xls";
        //处理中文下载名乱码
        try {
            fileName = new String(fileName.getBytes("gbk"), "iso-8859-1");
            //设置 response
            resp.setContentType("application/vnd.ms-excel");
            resp.setHeader("content-disposition", "attachment;filename=" + fileName);
            //workbook.write(resp.getOutputStream());
            workbook.write(new FileOutputStream(new File("E:/user.xls")));
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }
    }

    @ResponseBody
    @RequestMapping("select")
    public Map<String, List<Integer>> select(HttpServletResponse response) throws Exception {
        Map<String, List<Integer>> map = userService.findByMonthAndSex();
        System.out.println("-------------------------------");
        Set set = map.keySet();
        set.forEach(a -> System.out.println(map.get(a)));
        System.out.println("-------------------------------");
        return map;
    }
}

package com.baizhi.service.impl;

import com.baizhi.dao.StarDao;
import com.baizhi.entity.Star;
import com.baizhi.service.StarService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service("starService")
@Transactional
public class StarServiceImpl implements StarService {

    @Autowired
    private StarDao starDao;

    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Star star = new Star();
        List<Star> stars = starDao.selectByRowBounds(star, rowBounds);
        Integer count = starDao.selectCount(star);
        map.put("page", page);//当前页码
        map.put("rows", stars);//当前页显示的记录
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);//总页数
        map.put("records", count);//总记录数
        return map;
    }

    @Override
    public String add(Star star) {
        //1.添加明星信息进数据库
        //补全信息
        try {
            star.setId(UUID.randomUUID().toString());
            //修改图片名
            star.setPhoto(new Date().getTime() + "_" + star.getPhoto());
            starDao.insertSelective(star);
            return star.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加明星失败");
        }
    }

    @Override
    public void delete(String id, HttpServletRequest request) {
        //0.删除数据库中明星信息用带关联关系的删除
        try {
            Star star = starDao.selectByPrimaryKey(id);
            //删除明星信息成功
            starDao.deleteByPrimaryKey(id);
            //将明星图片从数据库删除
            //找到照片的相对路径
            String photo = star.getPhoto();
            File file = new File(request.getServletContext().getRealPath("/star/img"), photo);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除明星照片失败");
        }
        //1.删除数据库中的明星信息
        //2.删除数据库中明星图片
    }

    @Override
    public void update(Star star) {
        if ("".equals(star.getPhoto())) {
            star.setPhoto(null);
        }
        try {
            System.out.println(star.getPhoto() + ".......................");
            starDao.updateByPrimaryKeySelective(star);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改明星信息失败");
        }
    }

    @Override
    public List<Star> selectAll() {
        List<Star> list = starDao.selectAll();
        return list;
    }

    @Override
    public Star select(String id) {
        Star star = new Star();
        star.setId(id);
        Star s = starDao.selectByPrimaryKey(star);
        return s;
    }
}

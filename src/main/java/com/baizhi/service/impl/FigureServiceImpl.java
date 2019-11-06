package com.baizhi.service.impl;

import com.baizhi.dao.FigureDao;
import com.baizhi.entity.Figure;
import com.baizhi.service.FigureService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service("figureService")
@Transactional
public class FigureServiceImpl implements FigureService {
    @Autowired
    private FigureDao figureDao;

    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        Figure figure = new Figure();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        //根据页码查询当前页显示的图片
        List<Figure> figures = figureDao.selectByRowBounds(figure, rowBounds);
        figures.forEach(figure1 -> System.out.println(figure1));

        //查询总图片数
        int count = figureDao.selectCount(figure);
        map.put("page", page);//当前页
        map.put("rows", figures);//查询到的当前页的所有图片
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);//总共有几页
        map.put("records", count);//总共有多少条数据
        return map;
    }

    @Override
    public String add(Figure figure) {
        //1.补全轮播图信息
        figure.setId(UUID.randomUUID().toString());
        figure.setUp_date(new Date());
        figure.setImgPath(new Date().getTime() + "_" + figure.getImgPath());
        System.out.println(figure.getImgPath() + "在数据库存储的文件路径");
        System.out.println(figure + "---------------------");
        int i = figureDao.insertSelective(figure);
        if (i == 0) throw new RuntimeException("添加轮播图失败");
        return figure.getId();
    }

    @Override
    public String update(Figure figure) {
        if ("".equals(figure.getImgPath())) {
            figure.setImgPath(null);
        }
        System.out.println(figure + "-------------------");
        try {
            int i = figureDao.updateByPrimaryKeySelective(figure);
            System.out.println(i + "-*-//*-//-/--/*//*-/*/*/*/*-/*-/*");
            return figure.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改轮播图异常");
        }
    }

    @Override
    public void delete(String id, HttpServletRequest request) {
        //1.在删除轮播图前根据id查询到轮播图在服务器的存储位置
        Figure figure = figureDao.selectByPrimaryKey(id);
        int i = figureDao.deleteByPrimaryKey(id);
        if (i == 0) {
            //删除失败  不进行下一步操作
        } else {
            //删除成功，将轮播图从服务器中移除
            //1.找到文件的相对路径
            String imgPath = figure.getImgPath();
            System.out.println(imgPath + "----------------");
            File file = new File(request.getServletContext().getRealPath("/figure/img/"), imgPath);
            try {
                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("删除轮播图图片失败");
            }

        }
    }
}

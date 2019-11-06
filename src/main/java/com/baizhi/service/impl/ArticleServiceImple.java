package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import io.goeasy.GoEasy;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("articleService")
@Transactional
public class ArticleServiceImple implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        Article article = new Article();
        List<Article> articles = articleDao.selectByRowBounds(article, rowBounds);
        articles.forEach(a -> System.out.println(a));
        int count = articleDao.selectCount(article);
        map.put("page", page);
        map.put("rows", articles);
        map.put("total", count % rows == 0 ? count / rows : count / rows + 1);//总页数
        map.put("records", count);//总记录数
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-e891702436bc4f33b60b17739844b6b2");
        goEasy.publish("test-channel", "www.baidu.com");
        return map;
    }

    @Override
    public void delete(String id) {
        try {
            articleDao.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除文章异常");
        }
    }

    @Override
    public String add(Article article) {
        article.setId(UUID.randomUUID().toString());
        article.setCreateDate(new Date());
        System.out.println("要添加的文章信息为 " + article);
        try {
            articleDao.insertSelective(article);
            return article.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("添加文章失败");
        }
    }

    @Override
    public void update(Article article) {
        try {
            articleDao.updateByPrimaryKeySelective(article);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("修改文章失败");
        }
    }
}

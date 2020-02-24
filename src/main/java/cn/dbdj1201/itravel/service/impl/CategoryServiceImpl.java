package cn.dbdj1201.itravel.service.impl;

import cn.dbdj1201.itravel.dao.CategoryDao;
import cn.dbdj1201.itravel.dao.impl.CategoryDaoImpl;
import cn.dbdj1201.itravel.domain.Category;
import cn.dbdj1201.itravel.service.CategoryService;
import cn.dbdj1201.itravel.util.JedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-02-24 11:42
 **/
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        List<Category> categories = new ArrayList<>();
        Long length = jedis.llen("categories");
        if (length < 1) {
            categories = categoryDao.findAll();
            categories.forEach(category -> {
                try {
                    jedis.rpush("categories", new ObjectMapper().writeValueAsString(category));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        } else {
            for (int i = 0; i < length; i++) {
                try {
                    categories.add(new ObjectMapper().readValue(jedis.lpop("categories"), Category.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return categories;
    }
}

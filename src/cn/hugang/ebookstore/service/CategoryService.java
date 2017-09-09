package cn.hugang.ebookstore.service;

import cn.hugang.ebookstore.dao.CategoryDao;
import cn.hugang.ebookstore.dao.CategoryDaoImpl;
import cn.hugang.ebookstore.vo.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
public class CategoryService {
    public List<Category> findAll() {
        CategoryDao dao = new CategoryDaoImpl();
        return dao.findAll();
    }
}

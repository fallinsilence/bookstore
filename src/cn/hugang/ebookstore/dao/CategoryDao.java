package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.vo.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
public interface CategoryDao {
    List<Category> findAll();
}

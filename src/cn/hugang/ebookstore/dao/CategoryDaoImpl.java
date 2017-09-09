package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.utils.JDBCUtils;
import cn.hugang.ebookstore.vo.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Category> list = queryRunner.query("select * from category", new BeanListHandler<Category>(Category.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

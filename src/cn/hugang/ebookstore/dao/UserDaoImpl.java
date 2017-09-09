package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.utils.JDBCUtils;
import cn.hugang.ebookstore.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public int register(User user) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            int rows = queryRunner.update("insert into user values(?, ?, ?, ?, ?, ?)", user.getUid(),
                    user.getUsername(), user.getEmail(), user.getPassword(), user.getActivecode(), user.getState());
            //插入失败
            if(rows >= 1){
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void modify() {

    }

    @Override
    public void delete() {

    }

    @Override
    public User findByName(User user) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            User u = queryRunner.query("select * from user where username = ? and password = ?", new BeanHandler<User>(User.class), user.getUsername(), user.getPassword());
            return u;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}

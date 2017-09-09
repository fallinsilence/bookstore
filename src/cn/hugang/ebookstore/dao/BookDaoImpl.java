package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.utils.JDBCUtils;
import cn.hugang.ebookstore.vo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> findById(String cid) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Book> list = queryRunner.query("select * from book where cid = ?", new BeanListHandler<Book>(Book.class), cid);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> findAll() {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            List<Book> list = queryRunner.query("select * from book", new BeanListHandler<Book>(Book.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book findByBid(String bid) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        try {
            Book book = queryRunner.query("select * from book where bid = ?", new BeanHandler<>(Book.class), bid);
            return book;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addBook(Book book) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "insert into book values (?,?,?,?,?,?,?)";
        try {
            queryRunner.update(sql,book.getBid(), book.getBname(), book.getPrice(), book.getAuthor(), book.getImage(), book.getCid(),"0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

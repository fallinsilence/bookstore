package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.utils.JDBCUtils;
import cn.hugang.ebookstore.vo.Book;
import cn.hugang.ebookstore.vo.Order;
import cn.hugang.ebookstore.vo.OrderItem;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/6.
 */
@SuppressWarnings("all")
public class OrderDaoImpl implements OrderDao {
    @Override
    public void save(Connection conn, Order order) {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orders values(?,?,?,?,?,?)";
        try {
            queryRunner.update(conn, sql, order.getOid(), order.getTotal(), order.getOrderTime(), order.getState(), order.getAddress(), order.getUser().getUid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Connection conn, OrderItem item) {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orderitem values(?,?,?,?,?)";
        try {
            queryRunner.update(conn, sql, item.getItemid(), item.getCount(), item.getSubtotal(), item.getBook().getBid(), item.getOrder().getOid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findById(String uid) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Order> list = null;
        String sql = "select * from orders where uid = ? order by ordertime desc";
        try {
            list = queryRunner.query(sql, new BeanListHandler<Order>(Order.class), uid);
            for(Order order : list){
                sql = "select * from orderitem o, book b where o.bid = b.bid and o.oid = ?";
                List<Map<String, Object>> listmap = queryRunner.query(sql, new MapListHandler(),order.getOid());
                    for(Map<String, Object> map : listmap ){
                        OrderItem item = new OrderItem();
                        BeanUtils.populate(item , map);
                        Book book = new Book();
                        BeanUtils.populate(book, map);
                        item.setBook(book);
                        item.setOrder(order);
                        order.getOrderItems().add(item);
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Order findByOid(String oid) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        List<Order> list = null;
        String sql = "select * from orders where oid = ?";
        try {
            list = queryRunner.query(sql, new BeanListHandler<Order>(Order.class), oid);
            for(Order order : list){
                sql = "select * from orderitem o, book b where o.bid = b.bid and o.oid = ?";
                List<Map<String, Object>> listmap = queryRunner.query(sql, new MapListHandler(),order.getOid());
                for(Map<String, Object> map : listmap ){
                    OrderItem item = new OrderItem();
                    BeanUtils.populate(item , map);
                    Book book = new Book();
                    BeanUtils.populate(book, map);
                    item.setBook(book);
                    item.setOrder(order);
                    order.getOrderItems().add(item);
                }
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

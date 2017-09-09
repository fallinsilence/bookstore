package cn.hugang.ebookstore.service;

import cn.hugang.ebookstore.dao.OrderDao;
import cn.hugang.ebookstore.dao.OrderDaoImpl;
import cn.hugang.ebookstore.utils.JDBCUtils;
import cn.hugang.ebookstore.vo.Order;
import cn.hugang.ebookstore.vo.OrderItem;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public class OrderService {
    public void save(Order order) {
        Connection conn = JDBCUtils.getConnection();
        try {
            conn.setAutoCommit(false);
            OrderDao dao = new OrderDaoImpl();
            dao.save(conn, order);
            for(OrderItem item : order.getOrderItems()){
                dao.save(conn, item);
            }
            DbUtils.commitAndCloseQuietly(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            DbUtils.rollbackAndCloseQuietly(conn);
        }
    }

    public List<Order> findById(String uid) {
        OrderDao dao = new OrderDaoImpl();
        return dao.findById(uid);
    }

    public Order findByOid(String oid) {
        OrderDao dao = new OrderDaoImpl();
        return dao.findByOid(oid);
    }
}

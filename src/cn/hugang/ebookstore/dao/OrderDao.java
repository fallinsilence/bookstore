package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.vo.Order;
import cn.hugang.ebookstore.vo.OrderItem;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */
public interface OrderDao {

    void save(Connection conn, Order order);

    void save(Connection conn, OrderItem item);

    List<Order> findById(String uid);

    Order findByOid(String oid);
}

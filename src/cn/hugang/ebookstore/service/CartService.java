package cn.hugang.ebookstore.service;

import cn.hugang.ebookstore.dao.BookDao;
import cn.hugang.ebookstore.dao.BookDaoImpl;
import cn.hugang.ebookstore.vo.Book;
import cn.hugang.ebookstore.vo.CartItem;

/**
 * Created by Administrator on 2017/9/5.
 */
public class CartService {
    public Book addCart(String bid) {
        BookDao dao = new BookDaoImpl();
        Book book = dao.findByBid(bid);
        CartItem item = new CartItem();
        item.setBook(book);
        return null;
    }
}

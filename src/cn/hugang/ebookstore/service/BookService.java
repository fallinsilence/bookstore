package cn.hugang.ebookstore.service;

import cn.hugang.ebookstore.dao.BookDao;
import cn.hugang.ebookstore.dao.BookDaoImpl;
import cn.hugang.ebookstore.vo.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
public class BookService {
    public List<Book> findById(String cid) {
        BookDao dao = new BookDaoImpl();
        return dao.findById(cid);
    }

    public List<Book> findAll() {
        BookDao dao = new BookDaoImpl();
        return dao.findAll();
    }

    public Book findByBid(String bid) {
        BookDao dao = new BookDaoImpl();
        return dao.findByBid(bid);
    }

    public void addBook(Book book) {
        BookDao dao = new BookDaoImpl();
        dao.addBook(book);
    }
}

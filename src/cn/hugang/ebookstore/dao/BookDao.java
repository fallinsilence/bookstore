package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.vo.Book;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
public interface BookDao {
    List<Book> findById(String cid);

    List<Book> findAll();

    Book findByBid(String bid);

    void addBook(Book book);
}

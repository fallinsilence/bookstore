package cn.hugang.ebookstore.servlet;

import cn.hugang.ebookstore.service.BookService;
import cn.hugang.ebookstore.vo.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */
@WebServlet(name = "BookServlet",urlPatterns = "/book")
public class BookServlet extends BaseServlet {
    public String findById(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        BookService service = new BookService();
        List<Book> list = service.findById(cid);
        request.setAttribute("list", list);
        return "/jsps/book/list.jsp";
    }

    public String findAll(HttpServletRequest request, HttpServletResponse response){
        BookService service = new BookService();
        List<Book> list = service.findAll();
        request.setAttribute("list", list);
        return "/jsps/book/list.jsp";
    }

    public String findByBid(HttpServletRequest request, HttpServletResponse response){
        String bid= request.getParameter("bid");
        BookService service = new BookService();
        Book book = service.findByBid(bid);
        request.setAttribute("book", book);
        return "/jsps/book/desc.jsp";
    }
}

package cn.hugang.ebookstore.servlet;

import cn.hugang.ebookstore.service.BookService;
import cn.hugang.ebookstore.vo.Book;
import cn.hugang.ebookstore.vo.Cart;
import cn.hugang.ebookstore.vo.CartItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/9/5.
 */
@WebServlet(urlPatterns = "/cart")
public class CartServlet extends BaseServlet {

    public Cart getCart(HttpServletRequest request){
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        return cart;
    }

    public String addCart(HttpServletRequest request, HttpServletResponse response){
        int count = Integer.parseInt(request.getParameter("count"));
        String bid = request.getParameter("bid");
        Book book = new BookService().findByBid(bid);

        Cart cart = getCart(request);
        CartItem item = new CartItem();
        item.setCount(count);
        item.setBook(book);
        cart.addCart(item);

        return "jsps/cart/list.jsp";
    }

    public String removeCart(HttpServletRequest request, HttpServletResponse response){
        String bid = request.getParameter("bid");
        Cart cart = getCart(request);
        cart.removeCart(bid);
        return "jsps/cart/list.jsp";
    }

    public String clearCart(HttpServletRequest request, HttpServletResponse response){
        Cart cart = getCart(request);
        cart.clearCart();
        return "jsps/cart/list.jsp";
    }
}

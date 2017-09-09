package cn.hugang.ebookstore.servlet;

import cn.hugang.ebookstore.service.CategoryService;
import cn.hugang.ebookstore.vo.Category;

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
@WebServlet(name = "CategoryServlet",urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    public String findAllCate(HttpServletRequest request, HttpServletResponse response){
        CategoryService service = new CategoryService();
        List<Category> list = service.findAll();
        request.setAttribute("list", list);
        return "/jsps/left.jsp";
    }
}

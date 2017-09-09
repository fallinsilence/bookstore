package cn.hugang.ebookstore.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传过来的方法名
        String method = req.getParameter("method");
        if (method != null) {
            Class clazz = this.getClass();
            try {
                //获取调用的方法
                Method m = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
                //调用方法
                String url = (String) m.invoke(this, req, resp);
                if(url != null){
                    req.getRequestDispatcher(url).forward(req, resp);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

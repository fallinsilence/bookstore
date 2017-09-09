package cn.hugang.ebookstore.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@WebFilter(filterName = "CharacterEncoding",urlPatterns = "/*")
public class CharacterEncoding implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        HttpServletRequest myReq = (HttpServletRequest) Proxy.newProxyInstance(CharacterEncoding.class.getClassLoader() ,
                new Class[]{HttpServletRequest.class} , new InvocationHandler() {

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String name = method.getName();
                        //如果方法名为getParameter或getParameterMap处理get,post乱码
                        if(name.equals("getParameter") || name.equals("getParameterMap")){
                            String type = request.getMethod();
                            if(type.equalsIgnoreCase("get")){

                            }else if(type.equalsIgnoreCase("post")){
                                request.setCharacterEncoding("utf-8");
                            }
                        }
                        return method.invoke(request , args);
                    }
                });
        chain.doFilter(myReq, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}


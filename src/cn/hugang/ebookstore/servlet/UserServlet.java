package cn.hugang.ebookstore.servlet;

import cn.hugang.ebookstore.service.UserService;
import cn.hugang.ebookstore.vo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet{
    public String register(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> map =  request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserService();
        int flag = service.register(user);
        if(flag == 0){
            request.setAttribute("msg","插入失败");
            return "jsps/user/regist.jsp";
        }
        request.setAttribute("msg", "插入成功");
        return "jsps/user/regist.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response){
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserService();
        User u = service.login(user);

        if(u != null){
            HttpSession session = request.getSession();
            session.setAttribute("existUser", u);
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
            return "index.jsp";
        }
        request.setAttribute("msg", "用户名或密码不正确");
        return "/jsps/user/login.jsp";
    }

    public String modify(HttpServletRequest request, HttpServletResponse response){
        return "";
    }

    public String exit(HttpServletRequest request, HttpServletResponse response){
        HttpSession zhanghu = request.getSession();
        zhanghu.invalidate();
        return "index.jsp";
    }

    public String findAll(HttpServletRequest request, HttpServletResponse response){

        return "";
    }
}

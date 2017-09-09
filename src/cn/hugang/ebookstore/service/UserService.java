package cn.hugang.ebookstore.service;

import cn.hugang.ebookstore.dao.UserDao;
import cn.hugang.ebookstore.dao.UserDaoImpl;
import cn.hugang.ebookstore.utils.UUIDUtils;
import cn.hugang.ebookstore.vo.User;

public class UserService {
    public int register(User user){
        user.setUid(UUIDUtils.getUUID());
        user.setActivecode(UUIDUtils.getUUID());
        user.setState(0);
        UserDao dao = new UserDaoImpl();
        int flag = dao.register(user);
        return flag;
    }

    public User login(User user) {
        UserDao dao = new UserDaoImpl();
        User u = dao.findByName(user);
        return u;
    }
}

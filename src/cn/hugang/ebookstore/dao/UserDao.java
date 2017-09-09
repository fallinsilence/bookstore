package cn.hugang.ebookstore.dao;

import cn.hugang.ebookstore.vo.User;

import java.util.List;

public interface UserDao {

    public void modify();

    public void delete();

    public User findByName(User user);

    public List<User> findAll();

    public int register(User user);
}

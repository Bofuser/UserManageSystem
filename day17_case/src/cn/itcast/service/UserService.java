package cn.itcast.service;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     *  用于查询所有用户信息
     * @return
     */
    public List<User> findAll();


    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 添加用户信息功能
     * @param user
     */
    void addUser(User user);


    /**
     * 删除用户信息功能
     * @param id
     */
    void deleteUser(String id);

    /**
     * 通过id查找用户信息
     * @param id
     */
    User findUserById(String id);

    /**
     * 修改用户信息
     * @param user
     */
    void update(User user);

    /**
     * 删除选中的用户信息功能
     * @param uids
     */
    void deleteSelectedUser(String[] uids);

    /**
     * 复杂条件分页查询
     * 页面查询数据功能
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition);
}

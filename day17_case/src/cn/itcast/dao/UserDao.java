package cn.itcast.dao;

import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 列表查询案例
     * 用sql语言查询数据库信息
     * @return
     */
    public List<User> findAll();


    /**
     * 登录案例
     * 查询用户名和密码是否一致
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username,String password);

    /**
     * 添加用户功能
     * @param user
     */
    void add(User user);

    /**
     * 删除用户功能
     * @param id
     */
    void delete(int id);

    /**
     * 寻找用户信息并赋值到修改功能
     * @param id
     */
    User findById(int id);

    /**
     * 修改用户信息功能
     * @param user
     */
    void update(User user);


    /**
     * 查询数据库总记录数
     * @param condition
     * @return
     */

    int findTotalCount(Map<String, String[]> condition);


    /**
     * 分页查询每页的记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}

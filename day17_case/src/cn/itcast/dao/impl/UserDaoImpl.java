package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.util.JDBCUtils;
import com.alibaba.druid.util.JdbcUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {

    //声明JDBCTemplate对象共用
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 列表查询案例
     * 使用sql语言查询
     * @return
     */

    @Override
    public List<User> findAll() {
        //1.使用sql语言查询
        String sql = "select * from user";

        //2.调用query方法返回数据库信息
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }


    /**
     * 登录案例
     * 查询用户名和密码是否一致
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {


        try {
            String sql = "select * from user where username = ? and password = ? ";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 添加用户功能
     * 将获取的用户信息存储到数据库中
     * @param user
     */
    @Override
    public void add(User user) {

        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";    //第一个null是id，不用重新赋值，它会自增。最后两个是用户名和密码，把它赋值为空就行了

        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());

    }

    /**
     * 通过id删除用户信息功能
     * @param id
     */
    @Override
    public void delete(int id) {

        String sql = "delete from user where id = ?";

        //执行sql语句
        template.update(sql,id);


    }


    /**
     * 通过id查找用户信息功能，并将信息赋值到修改页面的功能
     * @param id
     */
    @Override
    public User findById(int id) {
        //1.sql语句
        String sql = "select * from user where id = ?";
        //2.执行sql语句
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }


    /**
     * 修改信息后提交信息内容，和添加信息功能有点类似
     * @param user
     */
    @Override
    public void update(User user) {

        //1.sql语句
        String sql = "update user set name = ?,gender = ? ,age = ? , address = ? , qq = ?, email = ? where id = ?";

        //2.执行sql语句
        template.update(sql,user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(),user.getId());

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {

        //1.定义模板初始化sql
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
//        System.out.println(sb.toString());
//        System.out.println(params);

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {

        String sql = "select * from user  where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);
        //2.遍历map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {

            //排除分页条件参数
            if("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }

            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value != null && !"".equals(value)){
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
//        System.out.println(sql);
//        System.out.println(params);

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());
    }


}

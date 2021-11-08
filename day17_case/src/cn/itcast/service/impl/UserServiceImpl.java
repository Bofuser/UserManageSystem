package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.PageBean;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    //声明Dao层类
    private UserDao dao = new UserDaoImpl();

    /**
     * 调用dao层查询数据库信息
     * @return
     */

    @Override
    public List<User> findAll() {


        //调用dao层完成查询
        return dao.findAll();
    }


    /**
     * 登录方法，返回dao层数据
     * @param user
     * @return
     */

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }


    /**
     * 添加用户信息功能，将数据通过dao层传入数据库
     * @param user
     */
    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    /**
     * 通过id查找要删除的对象
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    /**
     * 通过id查找用户信息
     * @param id
     */
    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }


    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        dao.update(user);
    }

    /**
     * 删除选中的用户信息功能
     * 将传入过来的uids数组进行遍历，然后调用已有的deleteUser函数进行遍历删除。
     * @param uids
     */
    @Override
    public void deleteSelectedUser(String[] uids) {

        //为保证数据的安全性，需要加个if条件进行判断uids是否有传参
        if(uids != null && uids.length > 0){
            //遍历每一个传进来的id，然后调用dao层中已有的delete方法进行删除
            for (String id : uids) {
                dao.delete(Integer.parseInt(id));
            }
        }

    }

    /**
     * 页面查询功能
     * 通过当前页面页数和行数对数据进行查询和返回
     * @param _currentPage
     * @param _rows
     * @param condition
     * @return
     */
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {


        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //用于判断前端页面向前符号<<当在第一页时，防止其继续向前
        if(currentPage <=0) {
            currentPage = 1;
        }



        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();


        //3.调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        if(currentPage >= totalPage+1){
            currentPage = totalPage;
        }

        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        return pb;
    }
}

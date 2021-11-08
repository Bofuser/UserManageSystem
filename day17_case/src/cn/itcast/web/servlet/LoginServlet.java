package cn.itcast.web.servlet;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.设置request编码
        request.setCharacterEncoding("utf-8");

        //2.1获取输入的验证码
        String verifycode = request.getParameter("verifycode");

        //3.获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性

        //3.（改进代码）判断验证码是否正确可以简化，如果验证码错误就进入循环，并且通过return跳出doPost函数，不执行if后面的东西，
        //如果验证码正确就直接忽视if判断中的内容继续执行下面的东西。
        //验证码错误判断
        if(checkcode_server != null && !checkcode_server.equalsIgnoreCase(verifycode)){
            //输出失败信息
            request.setAttribute("login_msg","验证码错误！！！");
            //跳转回原来的页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);

            return;
        }


        //2.2获取参数Map集合
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //4.将用户信息封装到User对象
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);

        //判断用户是否存在
        if(loginUser != null){
            //密码正确
            //将信息存储到session中
            session.setAttribute("user",loginUser);
            //跳转到index.jsp中
            response.sendRedirect(request.getContextPath()+"/index.jsp");

        }else {
            //密码错误
            //输出错误信息
            request.setAttribute("login_msg","用户名或密码错误，请重新输入!");
            //跳回原来的界面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }










        /*//5.判断验证码是否一致，然后再判断用户名和密码(未优化代码)
        if(checkcode_server != null && checkcode_server.equalsIgnoreCase(verifycode)){
            //4.1判断验证码成功
            //4.1.1判断密码和用户名是否正确
            if(loginUser != null){
                //密码正确
                //将信息存储到session中
                session.setAttribute("user",loginUser);
                //跳转到index.jsp中
                response.sendRedirect(request.getContextPath()+"/index.jsp");


            }else {
                //密码错误
                //输出错误信息
                request.setAttribute("login_msg","用户名或密码错误，请重新输入!");
                //跳回原来的界面
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }

        }else {
            //4.2验证码失败
            //输出失败信息
            request.setAttribute("login_msg","验证码错误！！！");
            //跳转回原来的页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

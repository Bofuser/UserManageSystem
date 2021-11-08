package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录验证过滤器
 */

@WebFilter("/*")
public class LoginFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        /*//1.将req强制转换为HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) req;

        //2.获取资源请求路径
        String uri = request.getRequestURI();

        //3.判断是否包含登录相关的资源 login.jsp页面、css、js、font等等与登录页面相关的东西
        if (uri.contains("/login.jsp") || uri.contains("/loginServlet.jsp") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/checkCodeServlet")){

            //包含用户想登录则直接放行
            chain.doFilter(req, resp);
        }else {
           //不包含，则验证用户是否登录
           //1.从session中获取user
            Object user = request.getSession().getAttribute("user");
            System.out.println("user = "+user);
            //2.判断user对象是否为空，不是则表示登录
            if(user != null){
                //不是为空，则放行
                chain.doFilter(req, resp);
            }else {

                //否则，跳转回登录页面
                request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);

            }


        }*/

        System.out.println(req);
        //0.强制转换
        HttpServletRequest request = (HttpServletRequest) req;

        //1.获取资源请求路径
        String uri = request.getRequestURI();
        //2.判断是否包含登录相关资源路径,要注意排除掉 css/js/图片/验证码等资源
        if(uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/checkCodeServlet")  ){
            //包含，用户就是想登录。放行
            chain.doFilter(req, resp);
        }else{
            //不包含，需要验证用户是否登录
            //3.从获取session中获取user
            Object user = request.getSession().getAttribute("user");
            System.out.println("user = "+user);

            if(user != null){
                //登录了。放行
                chain.doFilter(req, resp);
            }else{
                //没有登录。跳转登录页面

                request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }



    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {

    }

}

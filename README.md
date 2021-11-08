# UserManageSystem

#### 介绍
学习黑马中做的一个小项目，一个简单的用户信息信息管理系统，基于Servlet + JSP + MySQL + JDBCTemplate + Druid + BeanUtils + tomcat

#### 软件架构
软件架构说明
整个项目中由“src” 和 “web”两个主要大包组成。
（1）在src包中,我们根据Java的三层架构模式，将其分成以下各个文件夹保存相应的各个功能文件。
以下为src中各个子包：

![输入图片说明](https://images.gitee.com/uploads/images/2021/1108/092639_524fe332_9204636.png "屏幕截图.png")


以下介绍各个项目中各个包所放的文件的功能：

 ①dao层（MyBatis框架)–>定义了对于数据库最基本的CRUD操作，包含实现增删改查功能的函数，需要输入sql函数对数据库进行访问

 ②domain层–>存放User和PageBean对象，用于对用户信息进行封装

 ③service层(Spring框架)–>调用组合dao层中的简单方法，形成复杂的功能。

 ④util层–>用来存放工具类的包，例如JDBCUtils工具类

 ⑤web层(SpringMVC层)–>里面包含各个功能函数板块的Servlet文件，用于接收用户参数、封装数据、调用service层完成处理后转发到jsp页面完成显示。

 ⑥druid.properties文件，用于链接MySQL数据库

(2)在web包中，用于放置前端UI展示页面和jsp文件


![输入图片说明](https://images.gitee.com/uploads/images/2021/1108/092715_9e6b4790_9204636.png "屏幕截图.png")


其中web中包含前端UI中所需要用到的css、font、js、jar包和jsp文件，其中WEB-INF用于存放jar包


#### 安装教程

该项目上传的为模块，使用idea的伙伴可以搜索“idea如何导入模块”便可将该项目部署。可参考该文章：https://blog.csdn.net/panguixiang5006/article/details/103228668?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522163633492916780265491008%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=163633492916780265491008&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-3-103228668.first_rank_v2_pc_rank_v29&utm_term=idea%E5%AF%BC%E5%85%A5%E6%A8%A1%E5%9D%97&spm=1018.2226.3001.4187

#### 使用说明

1.  该项目还有很多细节地方没有写到，具体可参考我写的博客：https://blog.csdn.net/qq_41891341/article/details/121193153
2.  为加强印象，可在B站搜索黑马的视频教程









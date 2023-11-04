package com.czx.bookproject.contoller;

import com.czx.bookproject.Bean.User;
import com.czx.bookproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    //登录验证
    @PostMapping("/judgeUserIfo")
    public String login(String username, String password, HttpSession httpSession){
        //判断密码和用户名是否正确
        boolean exist = userService.isCorrect(username, password);

        /*
        1.如果html页面中要引入别的HTML页面,那么他们都必须在static目录下才可以。

        2.由于添加了thymeleaf和springboot的整合包,其底层默认的前缀prefix:是/templates/
          后缀是/html。因此当控制器方法要跳转页面时,只能跳转到templates目录下的html页面。
          可以通过设置配置文件来修改前缀。

          修改前缀好处：
          这样就可以使得html页面和静态资源都存放在static目录下,
          从而引用文件时,可以正常引用！

         */

        if (exist){
            //将用户信息保存到session域中,为以后用于判断是否为登录状态
            httpSession.setAttribute("user",username);
            //跳转到首页!
            return "index";
        }else {
            httpSession.setAttribute("msg","用户名或密码错误!");
            //失败则跳转到还是在登录页面！
            return "login";
        }
    }

    //注册账号
    @PostMapping("/judgeRegister")
    public String register(User user,Model model){
        String username = user.getUsername();
        boolean existUsername = userService.isExistUsername(username);
        if (existUsername){
            model.addAttribute("msg","用户名已存在噢！");
            return "registration";
        }else {
            //将注册的用户信息保存在数据库中
            userService.addUser(user);
            //注册成功,访问登录页面
            return "login";
        }
    }
}

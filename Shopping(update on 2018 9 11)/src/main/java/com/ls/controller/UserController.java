package com.ls.controller;

import com.ls.dao.Upload;
import com.ls.entity.Goods;
import com.ls.entity.User;
import com.ls.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Upload upload;

    @Autowired
    private HttpSession session;

    private int pageNum = 5;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        return "redirect:/User/pageList?showNum=1";
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    public String pageList(Model model,
                           @RequestParam(value = "showNum", required = false) Integer showNum) {
        List<User> list = userService.getPageList(showNum, pageNum);
        //向上取整得到总页数
        model.addAttribute("pages", (int) Math.ceil(Double.parseDouble(userService.getTotalNum().toString()) / pageNum));
        model.addAttribute("showNum", showNum);
        model.addAttribute("list", list);
        return "user/User";
    }

    @RequestMapping(value = "/deleteOne", method = RequestMethod.GET)
    public String deleteOne(@RequestParam(value = "md5Id") String md5Id) {
        if (userService.deleteUserById(md5Id) < 1) {
            return "error/error";
        }
        return "redirect:/User/list";
    }

    @RequestMapping(value = "/Register", method = RequestMethod.GET)
    public String register() {
        //跳到登录页面
        return "user/Register";
    }

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public String userRegister(@RequestParam(value = "userName", required = false) String userName,
                               @RequestParam(value = "password", required = false) String password,
                               @RequestParam(value = "passwordRepeat", required = false) String passwordRepeat,
                               @RequestParam(value = "portrait", required = false) MultipartFile portrait,
                               @RequestParam(value = "phone", required = false) Long phone,
                               @RequestParam(value = "birth", required = false) String birth,
                               Model model) throws IOException, ParseException {
        if (userName == null || password == null || passwordRepeat == null || portrait.getSize() == 0 || phone == null || birth == null) {
            model.addAttribute("error", "请完善信息!");
            return "error/error";
        }
        if (!password.equals(passwordRepeat)) {
            model.addAttribute("error", "两次密码不一致!");
            return "error/error";
        }
        if (userService.repeatRegister(userName).size() > 0) {
            model.addAttribute("error", "该用户已被注册!");
            return "error/error";
        }
        //解决名字中文乱码
        userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        User user = new User(userName, password, upload.uploadUser(portrait), phone, format.parse(birth));
        if (userService.userRegister(user) < 1) {
            model.addAttribute("error", "注册失败!");
            return "error/error";
        }
        return "redirect:/User/Login";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String login() {
        //注销
        //session.invalidate();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        //跳到注册页面
        return "user/Login";
    }

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String userLogin(@RequestParam(value = "userName", required = false) String userName,
                            @RequestParam(value = "password", required = false) String password,
                            Model model) throws IOException {
        //解决名字中文乱码
        userName = new String(userName.getBytes("ISO-8859-1"), "UTF-8");

        User user = new User(userName, password);
        User userLogin = userService.userLogin(user);
        if (userLogin == null) {
            model.addAttribute("error", "查无此人!");
            return "error/error";
        }
        if (userLogin.getType() == 0) {
            //System.out.println("名字:"+userLogin.toString());
            session.setAttribute("user", userLogin);
            //跳到用户商品list的action
            return "redirect:/Goods/Customer/list";
        } else {
            //System.out.println("名字:"+userLogin.toString());
            session.setAttribute("user", userLogin);
            //跳到管理商品list的action
            return "redirect:/Goods/Manager/list";
        }
    }
}

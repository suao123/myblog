package com.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("admin")
public class admin_BlogController {

    //文章编辑
    @RequestMapping("editarticle")
    public String editarticle(){
        return "admin/editarticle";
    }

    //登陆跳转
    @RequestMapping("login")
    public String login(){
        return "admin/login";
    }

    //文章类型添加
    @RequestMapping("types")
    public String types(){
        return "admin/types";
    }
}

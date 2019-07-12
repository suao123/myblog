package com.blog.controller;

import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("blog")
public class BlogController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/listArticles")
    public String listArticles(){
        return "blog/listArticles";
    }
}

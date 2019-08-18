package com.blog.controller.admin;

import com.blog.model.Article;
import com.blog.service.ArticleService;
import com.blog.service.RedisService;
import com.blog.utils.DateKit;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Log4j2
@RestController
@RequestMapping("admin")
public class admin_BlogControllerHandle {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisService redisService;

    @Value("${file.uploadPic}")
    private String truePath;

    @RequestMapping(value = "editormdPic", consumes = "multipart/form-data")
    public HashMap editormdPic(@RequestParam(value = "editormd-image-file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        HashMap res = new HashMap();
        if(file.isEmpty()){
            res.put("message", "上传失败");
            res.put("success", " 0");
            res.put("url", "无");
            return res;
        }

        log.info("article上传图片");

        String filename = DateKit.getCurrentUnixTime()  + "_"+ file.getOriginalFilename();

        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(truePath + "/" + filename);
            Files.write(path, bytes);
        }catch (Exception e){
            e.printStackTrace();
        }

        res.put("success", 1);
        res.put("message", "图片上传成功！");
        res.put("url", "/static/uploadImage/" + filename);

        log.info("图片上传文件成功");
        return res;
    }

    @RequestMapping("submitBlog")
    public HashMap submitBlog(@RequestParam(value = "title", required = false)String title,
                                 @RequestParam(value = "type", required = false)String type,
                                 @RequestParam(value = "content", required = false)String content,
                                 @RequestParam(value = "htmlContent", required = false)String htmlContent,
                                 @RequestParam(value = "author", required = false)String author){
        HashMap<String, String> result = new HashMap<>();
        if (title.isEmpty()){
            result.put("word","文章标题没有写！！！");
        } else if (type.isEmpty()){
            result.put("word","文章分类没有写！！！");
        } else if (content.isEmpty() || htmlContent.isEmpty()){
            result.put("word", "文章没有内容");
        } else{
            Article article = new Article();
            article.setTitle(title);
            article.setAuthor(author);
            article.setType(type);
            article.setContent(content);
            article.setHtmlcontent(htmlContent);
            article.setCreated(DateKit.getCurrentUnixTime());
            article.setModified(null);
            articleService.publish(article);
            result.put("word", "发布成功");
        }
        return result;
    }

    @RequestMapping("checklogin")
    public HashMap checklogin(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        HashMap<String, String> result = new HashMap<>();
        if(username.equals("admin") && password.equals("password")){
            result.put("html", "editarticle");
        } else {
            result.put("html", "login");
        }
        return result;
    }

    @RequestMapping("getTypes")
    public Set getTypes(){
        return redisService.get("types");
    }

    @RequestMapping("addTypes")
    public HashMap addTypes(@RequestParam("type")String type){
        HashMap<String, String> result = new HashMap<>();
        if(!type.isEmpty()){
            redisService.set("types", type);
            result.put("mes", "上传成功");
        } else {
            result.put("mes", "上传失败");
        }
        return result;
    }
}

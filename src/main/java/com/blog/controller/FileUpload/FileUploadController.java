package com.blog.controller.FileUpload;

import com.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;


@Controller
@RequestMapping("file")
public class FileUploadController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/upload")
    public String upload(){
        return "file/upload";
    }

    @RequestMapping("/download")
    public String download(Model model){
        return "file/download";
    }
}

package com.blog.controller.FileUpload;

import com.blog.exception.TipException;
import com.blog.service.RedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;

@Log4j2
@RestController
@RequestMapping("file")
public class FileUploadControllerHandle {

    @Autowired
    private RedisService redisService;

    @Value("${file.upload}")
    private String path;

    @RequestMapping(value = "/file_upload", consumes = "multipart/form-data")
    public HashMap file_upload(@RequestParam(value = "file", required = false)MultipartFile file) throws Exception{
        HashMap<String, String> result = new HashMap<>();
        if(file.isEmpty()){
            throw new TipException("没有上传文件");
        } else {
            log.info("上传文件：");
        }
        String originalFileName = file.getOriginalFilename();
        log.info("文件名称为：" + originalFileName);

        File targetFile = new File(path, originalFileName);
        try{
            file.transferTo(targetFile);
            log.info(originalFileName + "文件传输成功");
            log.debug("写入redis中");
            redisService.set(originalFileName, originalFileName);
            log.info("写入成功");
            result.put("1", "成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

//    @GetMapping("/file_download")
//    public HashMap downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response){
//        HashMap<String, String> result = new HashMap<>();
//        if(fileName != null){
//            //设置下载路径
//            File file = new File(path + "/" + fileName);
//            if(file.exists()){
//                response.setContentType("application/force-download");//设置强制下载不打开
//                response.addHeader("Content-Disposition", "attachment;fileName="+fileName);//设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try{
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while(i != -1){
//                        os.write(buffer,0, i);
//                        i = bis.read(buffer);
//                    }
//                    result.put("1", "下载成功");
//                    return result;
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    if(bis != null){
//                        try{
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if(fis != null){
//                        try{
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        result.put("0", "下载失败");
//        return result;
//    }
}

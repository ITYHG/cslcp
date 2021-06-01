package com.zhisen.cslcp.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

	/**
	 * 文件上传
	 * @param file
	 * @param req
	 * @return
	 * @throws IOException
	 */
    @PostMapping("/import")
    public String importData(MultipartFile file, HttpServletRequest req) throws IOException {
    	SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String realPath ="D:\\file" + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();                             
        }
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder,newName));
       // String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/upload" + format + newName;
       // System.out.println(url);
        System.out.println(format+newName);
        return format+newName;
        
    }
    
    /**
	 * 文件上传
	 * @param file
	 * @param req
	 * @return
	 * @throws IOException
	 */
    @PostMapping("/importfiles")
    @ResponseBody
    public String importfiles(@RequestParam("file")  List<MultipartFile> files, HttpServletRequest req) throws IOException {
    	SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
        String format = sdf.format(new Date());
        String realPath ="D:\\file" + format;
        File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();                             
        }
        String filesaddress ="";
        String oldName;
        String newName;
        for(MultipartFile file : files){
        	oldName = file.getOriginalFilename();
            newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
            file.transferTo(new File(folder,newName));
            filesaddress =filesaddress + format+newName+",";
        }
        return filesaddress;
        
    }
}
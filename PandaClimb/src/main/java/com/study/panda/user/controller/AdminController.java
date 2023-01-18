package com.study.panda.user.controller;


import java.io.File;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/user")
public class AdminController {
	
	
	// 파일 이미지 경로

	
	@GetMapping(value="/admin.do") public String listView() throws Exception{
	
		return "/user/admin";
	}

	@PostMapping(value="/proUpload.do")
	@ResponseBody
	public String proUpload(@RequestParam("file1") MultipartFile multi, HttpServletRequest request, HttpServletResponse response, Model model){
	       
			String url = null;
	        
	        try {
	 
	            String uploadpath = "C:\\img";
	            System.out.println(uploadpath);
	            String originFilename = multi.getOriginalFilename();
	            String extName = originFilename.substring(originFilename.lastIndexOf("."),originFilename.length());
	            long size = multi.getSize();
	            String saveFileName = genSaveFileName(extName);
	            
	            System.out.println("uploadpath : " + uploadpath);
	            
	            System.out.println("originFilename : " + originFilename);
	            System.out.println("extensionName : " + extName);
	            System.out.println("size : " + size);
	            System.out.println("saveFileName : " + saveFileName);
	            
	            if(!multi.isEmpty())
	            {
	                File file = new File(uploadpath, saveFileName);
	                multi.transferTo(file);
	                
	                model.addAttribute("filename", saveFileName);
	                model.addAttribute("uploadPath", file.getAbsolutePath());
	                
	                return "false";
	            }
	        }catch(Exception e)
	        {
	            System.out.println(e);
	        }
		return "success";
	}
	
    private String genSaveFileName(String extName) {
        String fileName = "";
        
        Calendar calendar = Calendar.getInstance();
        fileName += calendar.get(Calendar.YEAR);
        fileName += calendar.get(Calendar.MONTH);
        fileName += calendar.get(Calendar.DATE);
        fileName += calendar.get(Calendar.HOUR);
        fileName += calendar.get(Calendar.MINUTE);
        fileName += calendar.get(Calendar.SECOND);
        fileName += calendar.get(Calendar.MILLISECOND);
        fileName += extName;
        
        return fileName;
    }
}



    
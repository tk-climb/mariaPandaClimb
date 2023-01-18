package com.study.panda.user.controller;


import java.io.File;
import java.util.Calendar;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/user")
public class AdminController {
	
	// 상대경로 지정 src/main/resource 부터 시작함.
	
	//	resource.getFile(); // 파일 객체
	//	resource.getFilename(); // 파일 이름
	//	resource.getInputStream() // InputStream 객체
	//	resource.getPath(); // 파일 경로
	//	resource.getURL(); // URL 객체
	//	resource.getURI(); // URI 객체
	ClassPathResource resource = new ClassPathResource("static/img");
	
	// 파일 이미지 경로
	private String path = resource.getPath();
	
	@GetMapping(value="/admin.do") public String listView() throws Exception{
	  
		return "/user/admin";
	}
	 
	@GetMapping(value="/proUpload.do")
	public String proUpload(@RequestParam("file1") MultipartFile multi, HttpServletRequest request, HttpServletResponse response, Model model){
	       
			String url = null;
	        
	        try {
	 
	            //String uploadpath = request.getServletContext().getRealPath(path);
	            String uploadpath = path;
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
	                File file = new File(uploadpath, multi.getOriginalFilename());
	                multi.transferTo(file);
	                
	                model.addAttribute("filename", multi.getOriginalFilename());
	                model.addAttribute("uploadPath", file.getAbsolutePath());
	                
	                return "filelist";
	            }
	        }catch(Exception e)
	        {
	            System.out.println(e);
	        }
		return "/user/admin";
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



    
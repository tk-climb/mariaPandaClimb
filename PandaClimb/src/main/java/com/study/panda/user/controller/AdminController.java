package com.study.panda.user.controller;


import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.panda.common.dto.AttachmentDto;
import com.study.panda.common.dto.CategoryDto;
import com.study.panda.common.dto.ProductAttachmentDto;
import com.study.panda.common.dto.ProductDto;
import com.study.panda.user.dao.AdminDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/user")
public class AdminController {
	
	@Autowired
	private AdminDao admindao;
	
	@GetMapping(value="/admin.do") public String listView(Model model) throws Exception{
		
		//관리자 상품조회
		List<ProductDto> deleteList = admindao.deleteList(); // delete 리스트 조회
		model.addAttribute("deleteList", deleteList); // delete 리스트 담기
		
		return "/user/admin";
	}
	
	
	// 관리자 파일 업로드
	@PostMapping(value="/proUpload.do")
	@ResponseBody
	public String proUpload(@RequestParam("file1") MultipartFile multi, ProductAttachmentDto paDto,AttachmentDto attachmentDto, ProductDto productDto, CategoryDto cateDto,HttpServletRequest request, HttpServletResponse response, Model model){
	        
			// 파일 하나 업로드
	        try {
	            String uploadpath = "C:\\img";  // 파일경로
	            String originFilename = multi.getOriginalFilename(); // 넣은 파일 이름
	            String extName = originFilename.substring(originFilename.lastIndexOf("."),originFilename.length()); // .이전까지 파일이름 자르기
	            long size = multi.getSize();
	            String saveFileName = genSaveFileName(extName); // 파일이름 날짜로 변경
	            
	            attachmentDto.setAttachmentType(extName.replace("."," ")); // 타입형식 담기.
	            attachmentDto.setAttachmentSize(Long.toString(multi.getSize())); // 사이즈 담기.
	            attachmentDto.setAttachmentName(saveFileName); // 파일이름 담기.
	            
	            // multi파일이 비어있지 않으면 성공로직
	            if(!multi.isEmpty())
	            {
	            	// 상품 등록
	            	if(!admindao.productInsert(productDto)) {
	            		return "false";
	            	}
	            	// 첨부파일 등록
	            	if(!admindao.attachInsert(attachmentDto)) {
	            		return "false";
	            	}

	            	// 상품 조회
	            	List<Map<String, Object>> productList = admindao.productList(productDto);
	            	
	            	// 조회한결과
	            	int productAtNo = Integer.parseInt(productList.get(0).get("productAtNo").toString());
	            	String categorySub =  productList.get(0).get("categorySub").toString(); //카테고리 등록때 사용
	            	
	            	// 조회한결과 상품_첨부에 값 넣기
	            	paDto.setProductAtNo(productAtNo); // 상품_조회 insert사용
	            	paDto.setAttachmentNo(attachmentDto.getAttachmentNo()); //상품_조회 insert사용, keyProperty로 insert한 key값 셋팅
	            	cateDto.setCategoryMajor(productDto.getCategorySub()); //카테고리 메인
	            	cateDto.setCategorySub(categorySub); //카테고리 세부
	            	
	            	// 상품_첨부등록
	            	if(!admindao.productAttachInsert(paDto)) {
	            		return "false";
	            	}
	            	
	            	//카테고리 등록
	            	if(!admindao.categoryInsert(cateDto)) {
	            		return "false";
	            	}
	            	
	            		//이미지파일 등록
		                File file = new File(uploadpath, saveFileName);
		                multi.transferTo(file);
		                
		                model.addAttribute("filename", saveFileName);
		                model.addAttribute("uploadPath", file.getAbsolutePath());
		                
		                return "success";
	            }
	        }catch(Exception e)
	        {
	            System.out.println(e);
	        }
		return "fail";
	}
	
	// 파일 이름 날짜로 설정
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
    
    // 관리자 상품 조회
    
}



    
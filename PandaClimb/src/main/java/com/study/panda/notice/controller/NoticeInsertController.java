package com.study.panda.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.panda.common.dto.NoticeDto;
import com.study.panda.notice.dao.NoticeDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@GetMapping(value="/insert.do")
	public String insert() {
		return "/notice/insert";
	}
	
	@PostMapping(value="/insert.do")
	public String insert(HttpSession session,@ModelAttribute NoticeDto noticeDto)  {

		Integer loginNo =  (Integer) session.getAttribute("userNo");
		noticeDto.setUserNo(loginNo);
	//	System.out.println(noticeDto.getUserNo());
		noticeDao.insert(noticeDto);

		return "redirect:list.do";
}
	
}

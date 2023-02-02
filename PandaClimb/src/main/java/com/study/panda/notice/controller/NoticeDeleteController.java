package com.study.panda.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.panda.notice.dao.NoticeDao;

@Controller
@RequestMapping("/notice")
public class NoticeDeleteController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@GetMapping("/delete.do")
	public String delete(@RequestParam int noticeNo) {
		noticeDao.delete(noticeNo);
		return "redirect:list.do";
		}	
	
}

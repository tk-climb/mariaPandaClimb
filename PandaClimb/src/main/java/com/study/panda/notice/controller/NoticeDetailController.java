package com.study.panda.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.panda.common.dto.NoticeDto;
import com.study.panda.notice.dao.NoticeDao;

@Controller
@RequestMapping("/notice")
public class NoticeDetailController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@GetMapping("/detail.do")
	public String detail(Model model, @RequestParam int noticeNo) {
		NoticeDto noticeDto = noticeDao.detail(noticeNo);
		model.addAttribute("noticeDto", noticeDto);
		return "/notice/detail";
	}
	
}

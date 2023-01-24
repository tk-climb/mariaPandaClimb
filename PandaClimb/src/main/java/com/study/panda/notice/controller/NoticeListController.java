package com.study.panda.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.panda.common.dto.NoticeDto;
import com.study.panda.notice.dao.NoticeDao;

@Controller
@RequestMapping("/notice")
public class NoticeListController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@GetMapping("/list.do")
	public String list(Model model, @ModelAttribute NoticeDto noticeDto) {
		List<NoticeDto> noticeList = noticeDao.selectList(noticeDto);
		model.addAttribute("noticeList", noticeList);
		return "/notice/list";
	}
	
}

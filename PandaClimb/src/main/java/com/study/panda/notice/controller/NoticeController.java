package com.study.panda.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.panda.common.dto.NoticeDto;
import com.study.panda.notice.dao.NoticeDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	// 1:1 문의 등록
	@GetMapping(value="/insert.do")
	public String insert() {
		return "/notice/insert";
	}
	
	@PostMapping(value="/insert.do")
	public String insert(HttpSession session,@ModelAttribute NoticeDto noticeDto, 
			RedirectAttributes attr)  {
		noticeDao.insert(noticeDto);
		return "redirect:list.do";
}
	@GetMapping("/list.do")
	public String list(Model model, @ModelAttribute NoticeDto noticeDto) {
		List<NoticeDto> noticeList = noticeDao.selectList();
		model.addAttribute("noticeList", noticeList);
		return "/notice/list";
	}
}

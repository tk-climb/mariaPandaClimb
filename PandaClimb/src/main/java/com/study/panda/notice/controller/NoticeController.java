package com.study.panda.notice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.panda.common.constant.SessionConstant;
import com.study.panda.common.dto.NoticeDto;
import com.study.panda.notice.dao.NoticeDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private NoticeDao noticeDao;
	
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
		List<NoticeDto> noticeList = noticeDao.selectList(noticeDto);
		model.addAttribute("noticeList", noticeList);
		return "/notice/list";
	}
	
	@GetMapping("/detail.do")
	public String detail(Model model, @RequestParam int noticeNo) {
		NoticeDto noticeDto = noticeDao.detail(noticeNo);
		model.addAttribute("noticeDto", noticeDto);
		return "/notice/detail";
	}
	
	// 문의 수정
	@GetMapping("/edit")
	public String edit() {
		return "/notice/edit";
	}
		
	@PostMapping("/edit")
	public String edit(@ModelAttribute NoticeDto noticeDto, RedirectAttributes attr) throws Exception {
		noticeDao.edit(noticeDto);
		attr.addAttribute("noticeNo", noticeDto.getNoticeNo());
		return "redirect:detail";
	}		
	
	// 문의 삭제
	@GetMapping("/delete")
	public String delete(@RequestParam int noticeNo, HttpSession session) {
		noticeDao.delete(noticeNo);
		return "redirect:list.do";
		}	
	
}

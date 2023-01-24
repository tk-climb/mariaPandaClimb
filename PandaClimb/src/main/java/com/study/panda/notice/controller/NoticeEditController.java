package com.study.panda.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.panda.common.dto.NoticeDto;
import com.study.panda.notice.dao.NoticeDao;

@Controller
@RequestMapping("/notice")
public class NoticeEditController {
	
	@Autowired
	private NoticeDao noticeDao;

	@GetMapping("/edit.do")
	public String edit(Model model, @RequestParam int noticeNo) {
		NoticeDto noticeDto = noticeDao.detail(noticeNo);
		model.addAttribute("noticeDto", noticeDto);
		return "/notice/edit";
	}
		
	@PostMapping("/edit.do")
	public String edit(@ModelAttribute NoticeDto noticeDto, RedirectAttributes attr) throws Exception {
		noticeDao.edit(noticeDto);
		attr.addAttribute("noticeNo", noticeDto.getNoticeNo());
		return "redirect:detail.do";
	}		
}

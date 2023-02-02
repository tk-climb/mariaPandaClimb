package com.study.panda.qna.controller;

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

import com.study.panda.common.dto.QnaDto;
import com.study.panda.common.dto.QnaReplyDto;
import com.study.panda.qan.dao.QnaDao;
import com.study.panda.qanReply.dao.QnaReplyDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/qna")
public class QnaEditController {

	@Autowired
	private QnaDao qnaDao;
	
	@GetMapping("/edit.do")
	public String edit(Model model, @RequestParam int qnaNo) {
		QnaDto qnaDto = qnaDao.detail(qnaNo);
		model.addAttribute("qnaDto", qnaDto);
		return "/qna/edit";
	}
		
	@PostMapping("/edit.do")
	public String edit(@ModelAttribute QnaDto qnaDto, RedirectAttributes attr) throws Exception {
		qnaDao.edit(qnaDto);
		attr.addAttribute("qnaNo", qnaDto.getQnaNo());
		return "redirect:detail.do";
	}
	
}

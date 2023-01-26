package com.study.panda.qnaReply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.panda.common.dto.QnaReplyDto;
import com.study.panda.qanReply.dao.QnaReplyDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/qnaReply")
public class QnaReplyController {

	@Autowired
	private QnaReplyDao qnaReplyDao;

	@PostMapping("/insert.do")
	public String insert(HttpSession session,@ModelAttribute QnaReplyDto qnaReplyDto,RedirectAttributes attr)  {
		int qnaNo = qnaReplyDto.getQnaNo();
		boolean result = qnaReplyDao.insert(qnaReplyDto);
		if(result) {
			qnaReplyDao.updateReply(qnaReplyDto); // 답변 상태 업뎃
		 }
		attr.addAttribute("qnaNo", qnaNo);
		return "redirect:/qna/detail.do";
	}
		
	@PostMapping("/editReply.do")
	public String editReply(@ModelAttribute QnaReplyDto qnaReplyDto, RedirectAttributes attr) throws Exception {
		boolean result = qnaReplyDao.editReply(qnaReplyDto);
		
		if(result) {
			int qnaNo = qnaReplyDto.getQnaNo();
			attr.addAttribute("qnaNo", qnaNo);
			return "redirect:/qna/detail.do";
		}
		else { 
			throw new Exception();
		}
	}
	
	@GetMapping("/delete.do")
	public String delete(@ModelAttribute QnaReplyDto qnaReplyDto, RedirectAttributes attr) throws Exception {

		boolean result =qnaReplyDao.delete(qnaReplyDto);
		if(result) {
			int qnaNo = qnaReplyDto.getQnaNo();
			attr.addAttribute("qnaNo", qnaNo);
			return "redirect:/qna/detail.do";
		}
		else {
			throw new Exception();
		}
	}	
	
	
}

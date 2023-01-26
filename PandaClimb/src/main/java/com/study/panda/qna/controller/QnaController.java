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
public class QnaController {

	@Autowired
	private QnaDao qnaDao;
	@Autowired
	private QnaReplyDao qnaReplyDao;

	@GetMapping("/insert.do")
	public String insert() {
		return "/qna/insert";
	}
	
	@PostMapping("/insert.do")
	public String insert(HttpSession session,@ModelAttribute QnaDto qnaDto)  {
		qnaDao.insert(qnaDto);
		return "redirect:list.do";
	}
	@GetMapping("/list.do")
	public String list(Model model, @ModelAttribute QnaDto qnaDto) {
		List<QnaDto> qnaList = qnaDao.selectList(qnaDto);
		model.addAttribute("qnaList", qnaList);
		return "/qna/list";
	}
	
	@GetMapping("/detail.do")
	public String detail(Model model, @RequestParam int qnaNo, @ModelAttribute QnaReplyDto qnaReplyDto) {
		QnaDto qnaDto = qnaDao.detail(qnaNo);
		model.addAttribute("qnaDto", qnaDto);
		
		// 댓글
		List<QnaReplyDto> replyList = qnaReplyDao.selectList(qnaNo, qnaReplyDto);
		model.addAttribute("replyList", replyList);
		return "/qna/detail";
	}
	
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
	
	@GetMapping("/delete.do")
	public String delete(@RequestParam int qnaNo) {
		qnaDao.delete(qnaNo);
		return "redirect:list.do";
		}	
}

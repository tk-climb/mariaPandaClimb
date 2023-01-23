package com.study.panda.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.panda.common.dao.HomeDao;
import com.study.panda.common.dto.NoticeDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private HomeDao homeDao;
    
	  @GetMapping(value="/home.do") public String listView() throws Exception{
		  
		  return "/home"; }


	/*
	 * @GetMapping(value="/list.do") public String listView(Model model, NoticeDto
	 * noticeDto) throws Exception{ List<NoticeDto> AllList =
	 * homeDao.listView(noticeDto); model.addAttribute("AllList",AllList);
	 * model.addAttribute("greet","greeting");
	 * System.out.println("@@@@@@@ 최종값은? @@@@"+AllList); return "/home"; }
	 */
    
	/*
	 * @GetMapping(value="/insert") public String insert() { return "notice/insert";
	 * }
	 * 
	 * @PostMapping(value="/insert") public String insert(HttpSession
	 * session,@ModelAttribute NoticeDto noticeDto, RedirectAttributes attr) {
	 * System.out.println(noticeDto); homeDao.insert(noticeDto);
	 * 
	 * return "redirect:list"; }
	 */
    
}

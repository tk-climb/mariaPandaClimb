package com.study.panda.user.controller;

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
import com.study.panda.common.dto.UserDto;
import com.study.panda.user.dao.UserDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class EditController {

	@Autowired
	private UserDao userDao;
	
	// 회원 정보 수정
	@GetMapping("/edit.do")
	public String edit(Model model, @RequestParam String userId) {
		UserDto userDto = userDao.selectOne(userId);
		model.addAttribute("userDto", userDto);
		return "/user/edit";
	}
	
	@PostMapping("/edit.do")
	public String edit(@ModelAttribute UserDto userDto, HttpSession session, RedirectAttributes attr) {
		String loginId = (String) session.getAttribute(SessionConstant.ID);
		boolean result = userDao.update(userDto);
	
		if (result) {
			attr.addAttribute("userId", userDto.getUserId());
			
			return "redirect:mypage.do";
		} else {
			return "redirect:edit?error&userId=";
		}
	}
}
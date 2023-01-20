package com.study.panda.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.panda.common.constant.SessionConstant;
import com.study.panda.user.dao.UserDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class MypageController {

	@Autowired
	private UserDao userDao;
	
	// 마이페이지
	@GetMapping("/mypage.do")
	public String mypage(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute(SessionConstant.ID);
		model.addAttribute("userDto", userDao.selectOne(loginId));
		return "/user/mypage";
	}
}
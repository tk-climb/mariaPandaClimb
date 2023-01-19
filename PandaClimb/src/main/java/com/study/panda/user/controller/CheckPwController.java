package com.study.panda.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.panda.common.constant.SessionConstant;
import com.study.panda.common.dto.UserDto;
import com.study.panda.user.dao.UserDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class CheckPwController {

	@Autowired
	private UserDao userDao;
	
	// 마이페이지 비밀번호 변경
	@GetMapping("/password.do")
	public String password() {
		return "/user/password";
	}
	
	@PostMapping("/password.do")
	public String password(UserDto userDto, HttpSession session, @RequestParam String beforePw, // 사용자가 입력한 기존 비밀번호
	@RequestParam String afterPw) {
	String userId = (String) session.getAttribute(SessionConstant.ID);

	// 비밀번호 검사
	UserDto userDto1 = userDao.selectOne(userId);
	boolean passwordMatch = beforePw.equals(userDto1.getUserPw()); // 원본 비밀번호 랑 찾은 비밀번호랑 비교
//	System.out.println(passwordMatch);
	
	if (!passwordMatch) {
		return "redirect:password.do?error&userId=" + userId;
	}
	else {
	userDao.changePw(userDto); // 변경
	return "redirect:mypage.do";
} 
}
}
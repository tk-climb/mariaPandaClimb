package com.study.panda.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.panda.common.constant.SessionConstant;
import com.study.panda.common.dto.UserDto;
import com.study.panda.user.dao.UserDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class GoodbyeController {

	@Autowired
	private UserDao userDao;
	
	// 회원 탈퇴
			@GetMapping("/goodbye.do")
			public String goodbye() {
				return "/user/goodbye";
			}
			
			@PostMapping("/goodbye.do")
			public String goodbye(HttpSession session, @RequestParam String userPw) {
			String userId = (String)session.getAttribute(SessionConstant.ID);
			UserDto userDto = userDao.selectOne(userId);
			boolean passwordMatch = userPw.equals(userDto.getUserPw()); // 원본 비밀번호 랑 찾은 비밀번호랑 비교
			//System.out.println(passwordMatch);
			if(passwordMatch) {
				//회원 탈퇴
				userDao.delete(userId);
				//로그 아웃
				session.removeAttribute(SessionConstant.ID);
				session.removeAttribute(SessionConstant.GRADE);
				return "redirect:goodbye_result.do";
			}
			else {
				return "redirect:goodbye.do?error";
			}
		}
		
		// 탈퇴 성공 페이지
		@GetMapping("/goodbye_result.do")
		public String goodbyeResult() {
			return "/user/goodbyeResult";
		}
}
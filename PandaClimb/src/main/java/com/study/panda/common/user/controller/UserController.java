package com.study.panda.common.user.controller;

import java.util.HashMap;
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

import com.study.panda.common.constant.SessionConstant;
import com.study.panda.common.dto.UserDto;
import com.study.panda.user.dao.UserDao;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDao userDao;
	
	 @GetMapping(value="/join.do")
		public String join() {
			return "/user/join";
		}
	    
	    @PostMapping(value="/join.do")
		public String join(@ModelAttribute UserDto userDto) {
	    	userDao.join(userDto);
	    	return "redirect:joinSuccess.do";
	    }
	    
	    // 가입완료
		@GetMapping(value="/joinSuccess.do")
		public String joinSuccess() {
			return "/user/joinSuccess";
		}
			
			// 로그인
		@GetMapping(value="/login.do")
		public String login() {
			return "/user/login";
		}

		@PostMapping(value="/login.do")
		@ResponseBody
		public Object login(HttpSession session, @RequestParam String userPw, @RequestParam String userId, Model model, @ModelAttribute UserDto userDto) {
	
			Map<String,String>map = new HashMap<String,String>();
			map.put("userId", userId);
			map.put("userPw", userPw);
			int userLoign = userDao.login(map);
			
			if (userLoign > 0) {
				session.setAttribute(SessionConstant.ID, userDto.getUserId());
				session.setAttribute(SessionConstant.GRADE, userDto.getUserGrade());
			//로그인 시간 갱신
				userDao.updateLoginTime(userDto.getUserId());
				
				return "redirect:/login.do";
			} else {
				map.put("message", "입력된 회원정보가 존재하지 않습니다");
				model.addAttribute("userLoign", userLoign);
				return map;
			}
			
		}

		// 로그아웃
		@GetMapping("/logout.do")
		public String logout(HttpSession session) {
			session.removeAttribute(SessionConstant.ID);
			session.removeAttribute(SessionConstant.GRADE);
			
			return "redirect:login.do";
		}
}
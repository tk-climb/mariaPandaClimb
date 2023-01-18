package com.study.panda.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.panda.common.dto.UserDto;
import com.study.panda.user.dao.UserDao;

@Controller
@RequestMapping("/user")
public class JoinController {

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
}
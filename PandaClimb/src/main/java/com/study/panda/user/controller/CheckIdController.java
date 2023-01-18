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
public class CheckIdController {

	@Autowired
	private UserDao userDao;

		// 아이디 찾기
		@GetMapping("/checkId.do")
		public String checkId() {
			return"/user/checkId";
		}
		
		@PostMapping("/checkId.do")
		public String checkId(@RequestParam String userEmail, Model model, HttpSession session) {
			List<Object> checkId = userDao.checkId(userEmail);
			
			if(checkId.isEmpty()) {
				return "redirect:checkId.do?error";
			}
			else {
				model.addAttribute("checkId", checkId);	
				//System.out.println(checkId);
			}
			return "/user/checkIdResult";
		}
		
		// 아이디 확인 페이지
			@GetMapping("/checkId_result.do")
			public String checkIdResult() {
				return "/user/checkIdResult";
			}
}
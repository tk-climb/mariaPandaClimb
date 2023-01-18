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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		// 로그인
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
			} 
			else {
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
		
		// 마이페이지
		@GetMapping("/mypage.do")
		public String mypage(Model model, HttpSession session) {
			String loginId = (String) session.getAttribute(SessionConstant.ID);
			model.addAttribute("userDto", userDao.selectOne(loginId));
			return "/user/mypage";
		}
		
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
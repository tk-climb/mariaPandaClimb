package com.study.panda.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.panda.common.component.RandomGenerator;
import com.study.panda.common.dto.CertificationDto;
import com.study.panda.common.dto.UserDto;
import com.study.panda.user.dao.CertificationDao;
import com.study.panda.user.dao.UserDao;

@Controller
@RequestMapping("/user")
public class PwController {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RandomGenerator randomGenerator; 

	@Autowired
	private CertificationDao certificationDao; 
	
	@Autowired
	private JavaMailSender javaMailSender; 
	
	// 비밀번호 찾기
	@GetMapping("/checkPw.do")
	public String checkPw() {
		return"/user/checkPw";
	}
	
	@PostMapping("/checkPw.do")
	@ResponseBody
	public Object checkPw(
			@RequestParam String userEmail, @RequestParam String userId, Model model) {
		
			Map<String,String>map = new HashMap<String,String>();
			map.put("userId", userId);
			map.put("userEmail", userEmail);
			
			int checkPw = userDao.checkPw(map);
			
			// 이메일 인증
			if(checkPw > 0) {
				// 1) 랜덤 인증 번호 생성 수정 구문
				String serial = randomGenerator.generatSerial(6);

				// 2) 이메일 발송
				SimpleMailMessage message = new SimpleMailMessage();
				message.setTo(userEmail);
				message.setSubject("[PANDA] 비밀번호 확인 인증 번호입니다");
				message.setText("인증번호 : " + serial);
				javaMailSender.send(message);

				// 3) 데이터베이스 등록 수정 구문
				certificationDao.delete(userEmail);
				CertificationDto certificationDto = CertificationDto.builder().certificationId(userEmail).certificationKey(serial).build();
				certificationDao.insert(certificationDto);
				
				map.put("message", "확인");
				//model.addAttribute("userId", userId);
				return "redirect:checkPw.do";
				}
			else {
				map.put("message", "입력된 회원정보가 존재하지 않습니다");
				model.addAttribute("checkPw", checkPw);
				return map;
			}
	}
			

			// 이메일 인증 후 비밀번호 변경
			@GetMapping("/checkPwSuccess.do")
			public String checkPwSuccess(@RequestParam String userId, Model model) {
				model.addAttribute("userId", userId);
				return"/user/checkPwSuccess";
			}
			
			@PostMapping("/checkPwSuccess.do")
			public String checkPwSuccess(UserDto userDto, @RequestParam String userId, @RequestParam String userPw) {
				System.out.println("11"+userId);
				userDto.setUserId(userId);
				System.out.println("22"+userDto);
				boolean result = userDao.checkPwSuccess(userDto);
				System.out.println("33"+result);
				if (result) {
					return "redirect:checkPwdSuccess.do";
				} else {
					return "redirect:checkPwSuccess.do?error";
				}
			}
			
			// 이메일 인증 후 비밀번호 변경 성공 페이지
			@GetMapping("/checkPwdSuccess.do")
			public String checkPwdSuccess() {
				return "/user/checkPwdSuccess";
			}
}
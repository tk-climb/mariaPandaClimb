package com.study.panda.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.panda.user.dao.UserDao;

@CrossOrigin
@RestController
@RequestMapping("/rest/userId")
public class UserJoinCheckIdController {

	@Autowired
	private UserDao userDao;
	
	@PostMapping("/userId")
	private String userId (@RequestParam String value) {
		if(userDao.selectOne(value) == null) {
			return "NNNNY";
		}
		else {
			return "NNNNN";
		}
	}

}

package com.study.panda.user.dao;

import java.util.List;
import java.util.Map;

import com.study.panda.common.dto.UserDto;

public interface UserDao {

	void join(UserDto userDto); // 회원 가입
	
	UserDto selectOne(String userId); // 회원 단일 조회

	int login(Map map); //로그인
	
	boolean updateLoginTime(String userId); // 로그인 시간 갱신 입력
}

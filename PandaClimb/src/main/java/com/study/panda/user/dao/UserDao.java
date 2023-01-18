package com.study.panda.user.dao;

import java.util.List;
import java.util.Map;

import com.study.panda.common.dto.UserDto;

public interface UserDao {

	void join(UserDto userDto); // 회원 가입

	int login(Map map); //로그인
	
	boolean updateLoginTime(String userId); // 로그인 시간 갱신 입력
	
	UserDto selectOne(String userId); // 회원 단일 조회
	
	boolean update(UserDto userDto); // 회원 정보 수정
	
	boolean changePw(UserDto userDto); // 마이페이지 비밀번호 변경
	
	boolean delete(String userId); // 회원 탈퇴
	
	List<Object> checkId(String userEmail); // 아이디 찾기
}

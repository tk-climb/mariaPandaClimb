package com.study.panda.user.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void join(UserDto userDto) {
			sqlSession.insert("com.study.panda.user.dao.UserDaoImpl.join", userDto); 
		}

	@Override
	public UserDto selectOne(String userId) {
		return  sqlSession.selectOne("com.study.panda.user.dao.UserDaoImpl.get", userId);
	}
	
	@Override
	public int login(Map map) {
		return sqlSession.selectOne("com.study.panda.user.dao.UserDaoImpl.login",map);
		
	}

	@Override
	public boolean updateLoginTime(String userId) {
		return sqlSession.update("com.study.panda.user.dao.UserDaoImpl.loginTime", userId) > 0;
	}

}

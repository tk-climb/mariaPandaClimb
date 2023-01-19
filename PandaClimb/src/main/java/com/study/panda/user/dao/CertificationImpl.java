package com.study.panda.user.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.CertificationDto;

@Repository
public class CertificationImpl implements CertificationDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(CertificationDto certificationDto) {
		sqlSession.insert("com.study.panda.user.dao.CertificationImpl.insert", certificationDto);
	}

	@Override
	public boolean check(CertificationDto certificationDto) {
		CertificationDto result = sqlSession.selectOne("com.study.panda.user.dao.CertificationImpl.check", certificationDto);
		return result != null;
	}

	@Override
	public boolean delete(String certificationId) {
		int count = sqlSession.delete("com.study.panda.user.dao.CertificationImpl.delete", certificationId);
		return count>0;
	}

	@Override
	public CertificationDto selectOne(String certificationId) {
		return sqlSession.selectOne("com.study.panda.user.dao.CertificationImpl.get", certificationId);
	}	
	

}

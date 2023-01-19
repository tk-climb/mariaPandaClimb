package com.study.panda.user.dao;


import com.study.panda.common.dto.CertificationDto;

public interface CertificationDao {
	
	void insert(CertificationDto certificationDto);
	boolean check(CertificationDto certificationDto);
	boolean delete(String certificationId);
	
	CertificationDto selectOne(String certificationId); 
}
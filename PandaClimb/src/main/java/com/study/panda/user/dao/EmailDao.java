package com.study.panda.user.dao;

import com.study.panda.common.dto.CertificationDto;

public interface EmailDao {

	void sendCertMail(String email);
	boolean checkCert(CertificationDto certificationDto);
}

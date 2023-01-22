package com.study.panda.common.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CertificationDto {
	private String certificationId;
	private String certificationKey;
	private Date certificationIssueDate;
}

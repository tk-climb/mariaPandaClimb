package com.study.panda.common.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class AttachmentDto {
	private int attachmentNo;
	private String attachmentName;
	private String attachmentType;
	private String attachmentSize;
	private Date attachmentDate;
}

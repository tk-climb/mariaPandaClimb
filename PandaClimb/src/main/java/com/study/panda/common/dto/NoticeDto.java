package com.study.panda.common.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class NoticeDto {

	
	   private int noticeNo;
	   private String userNo; 
	   private String noticeTitle;
	   private String noticeContent;
	   private Date noticeDate;
	   private String noticeHead;
	   private String noticeHeader;
}

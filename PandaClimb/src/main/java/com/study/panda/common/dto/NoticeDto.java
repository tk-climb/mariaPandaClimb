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
	   
	   private int noticeNo1;
	   private String userNo2; 
	   private String noticeTitle3;
	   private String noticeContent4;
	   private Date noticeDate5;
	   private String noticeHead6;
}

package com.study.panda.common.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class QnaDto {

	   private int qnaNo;
	   private int userNo; 
	   private String qnaTitle;
	   private String qnaContent;
	   private Date qnaDate;
	   private String qnaState;
	   private int qnaAtNo;
}

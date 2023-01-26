package com.study.panda.common.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class QnaReplyDto {

	private int qnaReplyNo, userNo, qnaNo;
	private String qnaReplyContent;
	private Date qnaReplyDate;
	
}

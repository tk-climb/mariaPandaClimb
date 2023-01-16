package com.study.panda.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDto {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userNick;
	private String userEmail;
	private String userGrade;
	private String userIns;
	private String userLoginDate;
	

	

	
}

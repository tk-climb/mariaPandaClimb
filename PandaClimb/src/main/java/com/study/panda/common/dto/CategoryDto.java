package com.study.panda.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CategoryDto {
	
	private int categoryNo;
	private String categoryMajor;
	private String categorySub;
	private String categoryComment;
}

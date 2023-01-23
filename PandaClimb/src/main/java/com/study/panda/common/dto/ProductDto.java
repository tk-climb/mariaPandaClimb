package com.study.panda.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductDto {
	
	private int productNo;
	private String categorySub;
	private String productNm;
	private int productPrice;
	private int productAtNo;
	private String productComment;
	private int productCount;
	
}

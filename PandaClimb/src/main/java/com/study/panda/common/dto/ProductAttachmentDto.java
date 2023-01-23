package com.study.panda.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductAttachmentDto {
	private int paNo;
	private int productAtNo;
	private int attachmentNo;
}

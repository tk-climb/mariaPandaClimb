package com.study.panda.user.dao;

import java.util.List;
import java.util.Map;

import com.study.panda.common.dto.AttachmentDto;
import com.study.panda.common.dto.CategoryDto;
import com.study.panda.common.dto.ProductAttachmentDto;
import com.study.panda.common.dto.ProductDto;

public interface AdminDao {
	
	public boolean productInsert(ProductDto productDto);	// 상품 등록 panda_product
	public boolean attachInsert(AttachmentDto attachmentDto); 	// 첨부파일 등록 panda_attach
	public boolean productAttachInsert(ProductAttachmentDto paDto); 	// 상품_첨부 등록 product_attach
	public List<Map<String, Object>> productList(ProductDto productDto); // 등록한 상품 조회
	public boolean categoryInsert(CategoryDto cateDto); // 카테고리 등록
	public List<ProductDto> deleteList();
	
	
	

}

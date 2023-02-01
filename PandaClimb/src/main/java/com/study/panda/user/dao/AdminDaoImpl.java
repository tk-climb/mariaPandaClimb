package com.study.panda.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.AttachmentDto;
import com.study.panda.common.dto.CategoryDto;
import com.study.panda.common.dto.ProductAttachmentDto;
import com.study.panda.common.dto.ProductDto;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private SqlSession sqlSession;

	// 상품등록
	@Override
	public boolean productInsert(ProductDto productDto) {
		System.out.println("몇번오니?");
		return sqlSession.insert("com.study.panda.user.dao.AdminDaoImpl.productInsert", productDto) > 0;
	}
	
	// 첨부파일 등록
	@Override
	public boolean attachInsert(AttachmentDto attachmentDto) {
		return sqlSession.insert("com.study.panda.user.dao.AdminDaoImpl.attachInsert", attachmentDto) > 0;
	}
	
	// 상품_첨부 등록
	@Override
	public boolean productAttachInsert(ProductAttachmentDto paDto) {
		return sqlSession.insert("com.study.panda.user.dao.AdminDaoImpl.productAttachInsert", paDto) > 0;
	}
	
	// 카테고리 등록
	@Override
	public boolean categoryInsert(CategoryDto cateDto) {
		return sqlSession.insert("com.study.panda.user.dao.AdminDaoImpl.categoryInsert", cateDto) > 0;
	}

	// 방금 등록한 상품 조회
	@Override
	public List<Map<String, Object>> productList(ProductDto productDto){
		return sqlSession.selectList("com.study.panda.user.dao.AdminDaoImpl.productList",productDto);
	}
	
	// 관리자 삭제할 상품 조회
	@Override
	public List<ProductDto> deleteList(){
		return sqlSession.selectList("com.study.panda.user.dao.AdminDaoImpl.deleteList");
	}
	
	// 삭제할 첨부 1개 조회
	public List<Map<String, Object>> attachDelSearch(ProductDto productDto){
		return sqlSession.selectList("com.study.panda.user.dao.AdminDaoImpl.attachDelSearch", productDto);
	}
	
	// 상품 삭제
	@Override
	public boolean productDelete(ProductDto productDto) {
		return sqlSession.delete("com.study.panda.user.dao.AdminDaoImpl.productDelete", productDto) > 0;
	}
	
	// 첨부파일 삭제
	public boolean attachDelete(ProductAttachmentDto paDto) {
		return sqlSession.delete("com.study.panda.user.dao.AdminDaoImpl.attachDelete", paDto) > 0;
	}
	
	// 상품_첨부 삭제
	public boolean productAttachDelete(ProductDto productDto) {
		return sqlSession.delete("com.study.panda.user.dao.AdminDaoImpl.productAttachDelete", productDto) > 0;
	}
	
	// 카테고리 삭제
	public boolean categoryDelete(ProductDto productDto) {
		return sqlSession.delete("com.study.panda.user.dao.AdminDaoImpl.categoryDelete", productDto) > 0;
	}
	
}

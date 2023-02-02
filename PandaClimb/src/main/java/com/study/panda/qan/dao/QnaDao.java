package com.study.panda.qan.dao;

import java.util.List;

import com.study.panda.common.dto.NoticeDto;
import com.study.panda.common.dto.QnaDto;

public interface QnaDao {

	void insert(QnaDto qnaDto); // 문의 등록
	
	public QnaDto detail(int qnaNo); // 문의 상세보기
	
	List<QnaDto> selectList(QnaDto qnaDto); // 문의 목록
	
	boolean edit(QnaDto qnaDto); // 문의 수정
	
	boolean delete(int qnaNo); // 문의 삭제
}


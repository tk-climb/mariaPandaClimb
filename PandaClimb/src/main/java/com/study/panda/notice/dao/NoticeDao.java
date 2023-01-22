package com.study.panda.notice.dao;

import java.util.List;

import com.study.panda.common.dto.NoticeDto;

public interface NoticeDao {

	void insert(NoticeDto noticeDto); // 공지 등록
	
	public NoticeDto detail(int noticeNo); // 공지 상세보기
	
	List<NoticeDto> selectList(); // 공지 목록
}

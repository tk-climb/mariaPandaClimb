package com.study.panda.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.NoticeDto;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(NoticeDto noticeDto) {
		 sqlSession.insert("com.study.panda.common.dao.NoticeDaoImpl.insert", noticeDto);
	}

	@Override
	public NoticeDto detail(int noticeNo) {
		return sqlSession.selectOne("com.study.panda.common.dao.NoticeDaoImpl.detail", noticeNo);
	}

	@Override
	public List<NoticeDto> selectList() {
		return sqlSession.selectList("com.study.panda.common.dao.NoticeDaoImpl.list");
	}
	
	
}

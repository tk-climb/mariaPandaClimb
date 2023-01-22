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
	public List<NoticeDto> selectList(NoticeDto noticeDto) {
		return sqlSession.selectList("com.study.panda.common.dao.NoticeDaoImpl.list",noticeDto);
	}

	@Override
	public boolean edit(NoticeDto noticeDto) {
		int count = sqlSession.update("com.study.panda.common.dao.NoticeDaoImpl.edit", noticeDto);
		return count > 0;
	}

	@Override
	public boolean delete(int noticeNo) {
		int count = sqlSession.delete("com.study.panda.common.dao.NoticeDaoImpl.delete", noticeNo);
		return count > 0;
	}
	
	
}

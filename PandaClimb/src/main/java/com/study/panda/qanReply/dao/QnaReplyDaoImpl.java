package com.study.panda.qanReply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.QnaReplyDto;

@Repository
public class QnaReplyDaoImpl implements QnaReplyDao{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean insert(QnaReplyDto qnaReplyDto) {
		return sqlSession.insert("com.study.panda.common.dao.QnaReplyDaoImpl.insert", qnaReplyDto) > 0;
	}

	@Override
	public List<QnaReplyDto> selectList(int qnaNo, QnaReplyDto qnaReplyDto) {
		return sqlSession.selectList("com.study.panda.common.dao.QnaReplyDaoImpl.list",qnaReplyDto);
	}

	@Override
	public boolean editReply(QnaReplyDto qnaReplyDto) {
		int count = sqlSession.update("com.study.panda.common.dao.QnaReplyDaoImpl.edit", qnaReplyDto);
		return count > 0;
	}

	@Override
	public boolean delete(QnaReplyDto qnaReplyDto) {
		int count = sqlSession.delete("com.study.panda.common.dao.QnaReplyDaoImpl.delete", qnaReplyDto);
		return count > 0;
	}

	@Override
	public boolean updateReply(QnaReplyDto qnaReplyDto) {
		return sqlSession.update("com.study.panda.common.dao.QnaReplyDaoImpl.qnaUpdate", qnaReplyDto) > 0;
	}

}

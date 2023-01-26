package com.study.panda.qan.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.QnaDto;

@Repository
public class QnaDaoImpl implements QnaDao{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insert(QnaDto qnaDto) {
		 sqlSession.insert("com.study.panda.common.dao.QnaDaoImpl.insert", qnaDto);
		}

	@Override
	public QnaDto detail(int qnaNo) {
		return sqlSession.selectOne("com.study.panda.common.dao.QnaDaoImpl.detail", qnaNo);
		}


	@Override
	public List<QnaDto> selectList(QnaDto qnaDto) {
		return sqlSession.selectList("com.study.panda.common.dao.QnaDaoImpl.list",qnaDto);
	}

	@Override
	public boolean edit(QnaDto qnaDto) {
		int count = sqlSession.update("com.study.panda.common.dao.QnaDaoImpl.edit", qnaDto);
		return count > 0;
	}

	@Override
	public boolean delete(int qnaNo) {
		int count = sqlSession.delete("com.study.panda.common.dao.QnaDaoImpl.delete", qnaNo);
		return count > 0;
	}
}

package com.study.panda.qanReply.dao;

import java.util.List;

import com.study.panda.common.dto.QnaReplyDto;

public interface QnaReplyDao {

    boolean insert(QnaReplyDto qnaReplyDto); // 댓글 등록
	
	List<QnaReplyDto> selectList(int qnaNo, QnaReplyDto qnaReplyDto); // 댓글 목록
	
	boolean editReply(QnaReplyDto qnaReplyDto); // 댓글 수정
	
	boolean delete(QnaReplyDto qnaReplyDto); // 댓글 삭제
	
	boolean updateReply(QnaReplyDto qnaReplyDto); // 답변 업데이트
}


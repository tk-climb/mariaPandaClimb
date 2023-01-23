package com.study.panda.common.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.NoticeDto;

@Repository
public class HomeDaoImpl implements HomeDao {

	   @Autowired
	   private SqlSession sqlSession;
	   
	   @Override
	   public List<NoticeDto> listView(NoticeDto noticeDto){
	      return sqlSession.selectList("com.study.panda.common.dao.HomeDaoImpl.listView",noticeDto);
	   }

}


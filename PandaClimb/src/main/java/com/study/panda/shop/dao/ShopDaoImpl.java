package com.study.panda.shop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.panda.common.dto.ProductDto;

@Repository
public class ShopDaoImpl implements ShopDao {
	
	   @Autowired
	   private SqlSession sqlSession;
	   
		@Override
		public List<Map<String, Object>> shopList(){
			return sqlSession.selectList("com.study.panda.shop.dao.ShopDaoImpl.shopList");
		}
	   
}

package com.study.panda.shop.dao;

import java.util.List;
import java.util.Map;

import com.study.panda.common.dto.ProductDto;

public interface ShopDao {
	
	public List<Map<String, Object>> shopList(); // 방금 등록한 상품 조회
}

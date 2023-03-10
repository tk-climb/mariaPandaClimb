package com.study.panda.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.panda.shop.dao.ShopDao;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {

		@Autowired ShopDao shopdao;
		
		@GetMapping(value="/shop.do") public String listView(Model model) throws Exception{

			List<Map<String, Object>> shopList = shopdao.shopList();	
			
			model.addAttribute("shopList",shopList);
			
			System.out.println("제발 나와라 : " + model.getAttribute("shopList")); 
			
			return "/shop/shop";
			
		}


/*
 * @GetMapping(value="/list.do") public String listView(Model model, NoticeDto
 * noticeDto) throws Exception{ List<NoticeDto> AllList =
 * homeDao.listView(noticeDto); model.addAttribute("AllList",AllList);
 * model.addAttribute("greet","greeting");
 * System.out.println("@@@@@@@ 최종값은? @@@@"+AllList); return "/home"; }
 */

/*
 * @GetMapping(value="/insert") public String insert() { return "notice/insert";
 * }
 * 
 * @PostMapping(value="/insert") public String insert(HttpSession
 * session,@ModelAttribute NoticeDto noticeDto, RedirectAttributes attr) {
 * System.out.println(noticeDto); homeDao.insert(noticeDto);
 * 
 * return "redirect:list"; }
 */
    
}

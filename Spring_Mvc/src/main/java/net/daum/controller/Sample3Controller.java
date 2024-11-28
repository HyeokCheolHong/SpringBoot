package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.daum.vo.ProductVO;

@Controller
public class Sample3Controller {
	
	@RequestMapping(value="/namePrice", method=RequestMethod.GET)
	// namePrice 매핑주소 등록, GET으로 접근하는 매핑주소를 처리
	public ModelAndView namePrice() {
		ProductVO p = new ProductVO("노트북", 2500000);
		
		ModelAndView pm = new ModelAndView();
		pm.addObject("p", p);
		// 문자열 p 키 이름에 p객체 저장
		pm.setViewName("shop/noteBook");
		// 뷰리졸브 경로인 뷰페이지 경로는 WEB-INF/views/shop/noteBook.jsp를 읽어들인다.
		
		return pm;
	}
}

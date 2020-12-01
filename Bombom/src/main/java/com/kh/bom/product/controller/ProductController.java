package com.kh.bom.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.bom.common.page.AjaxPageBarFactory;
import com.kh.bom.product.model.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	
	@RequestMapping("/product/productAll") 
	public String allProduct() { 
		
		return "product/allList";
	}
	
	//상품문의 카운트 - 상품상세 첫화면
	@RequestMapping("/product/productOne")
	public ModelAndView productOne(ModelAndView mv,
			@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage) {
		
		mv.addObject("list", service.inquiryList(cPage,numPerpage));
		int totalData = service.inquiryCount();
		mv.addObject("count", totalData);
		mv.addObject("cPage", cPage);
		mv.addObject("pageBar",AjaxPageBarFactory.getAjaxPageBar(totalData, cPage, numPerpage, "productOneAjax"));
		mv.setViewName("product/productOne");

		return mv;
	}
	
	//상품문의 페이징처리
	@RequestMapping("/product/productOneAjax") 
	@ResponseBody
	public ModelAndView productOneAjax(ModelAndView mv, int cPage,
			@RequestParam(value="numPerpage",defaultValue="5") int numPerpage) {
	  
	 mv.addObject("list", service.inquiryList(cPage,numPerpage)); 
	 int totalData = service.inquiryCount(); 
	 mv.addObject("cPage", cPage); 
	 mv.addObject("pageBar", AjaxPageBarFactory.getAjaxPageBar(totalData, cPage, numPerpage, "productOneAjax")); 
	 mv.setViewName("product/productOneAjax");
	 
	 return mv; }
	 
	
}

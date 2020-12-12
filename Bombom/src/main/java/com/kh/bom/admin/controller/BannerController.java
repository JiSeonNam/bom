package com.kh.bom.admin.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.bom.admin.model.service.AdminService;
import com.kh.bom.admin.model.vo.MainBanner;
import com.kh.bom.product.model.vo.Product;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BannerController {
	@Autowired
	private AdminService service;

	// 배너 불러오기
	@RequestMapping("/admin/moveMainBanners")
	public ModelAndView moveBanner(ModelAndView mv) {
		List<MainBanner> list = service.selectBannerList();

		mv.addObject("list", list);
		mv.setViewName("admin/main/banner");
		return mv;
	}

	// 배너등록하기
	@RequestMapping("/admin/moveInsertBanner")
	public String moveInsertBanner(Model m) {
		List<Product> list = service.selectProductList();
		m.addAttribute("list", list);
		return "admin/main/BannerWrite";
	}

	@RequestMapping("/admin/insertBanner")
	public ModelAndView insertBanner(MainBanner mb, ModelAndView mv,
			@RequestParam(value = "bannerThumb", required = false) MultipartFile[] bannerThumb, HttpSession session) {

		log.debug("파일명 : {}", bannerThumb[0].getOriginalFilename());
		System.out.println("배너등록 : "+mb);

		// upload실제 경로 가져오기
		String path = session.getServletContext().getRealPath("resources/images/main/banner");
		// 폴더 생성
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdirs();
		
		// 다중파일업로드하기 MultipartFile객체의 transferTo()메소드이용 파일을 저장
		// file명을 재정의 하는것
		for (MultipartFile f : bannerThumb) {
			if (!f.isEmpty()) {
				String oriName = f.getOriginalFilename();
				try {
					f.transferTo(new File(path + "/" + oriName));
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				mb.setBannerThumb(oriName);
			}
		}

		// & 특수문자 치환하기
		String title = mb.getBannerTitle();
		String sub = mb.getBannerSubtitle();
		if (title.contains("&")) {
			title.replace("$", "'||chr(38)||'");
			mb.setBannerTitle(title);
		}
		if (sub.contains("&")) {
			sub.replace("$", "'||chr(38)||'");
			mb.setBannerSubtitle(sub);
		}

		int result = service.insertBanner(mb);

		String msg;
		String loc;
		String icon;
		if (result > 0) {
			msg = "등록에 성공했어요:)";
			loc = "admin/main/banner";
			icon = "success";// icon 종류 : success,error,warning
		} else {
			msg = "등록에 실패했어요:(";
			loc = "admin/main/banner";
			icon = "error";
		}
		mv.addObject("msg", msg);
		mv.addObject("loc", loc);
		mv.addObject("icon", icon);
		mv.setViewName("common/msg");

		return mv;
	}

}

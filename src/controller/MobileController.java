package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MobileController {
	
	@RequestMapping("/mobile")
	public ModelAndView go(HttpServletRequest request,
			HttpServletResponse response){
		
		System.out.println("모바일 페이지로 이동");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/index"); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
	}
	
	
	@RequestMapping("/mobile_registerForm")
	public ModelAndView mobile_registerForm(HttpServletRequest request,
			HttpServletResponse response){
		
		System.out.println("모바일 페이지로 이동");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/mobile_registerForm"); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
	}
}

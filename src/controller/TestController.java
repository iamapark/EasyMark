package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TestController {
	
	@RequestMapping("/go.do")
	public ModelAndView go(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="page") String page){
		System.out.println(page);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(page); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
	}
}

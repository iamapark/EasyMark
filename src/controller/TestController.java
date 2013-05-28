package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import dto.Login;
import dto.Member;


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
	
	@RequestMapping("/sleepPage")
	public ModelAndView sleepPage(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("MEMBERID");
		session.invalidate();
		Member m = new MembershipServiceImpl().getMemberInfo(userId);
		
		System.out.println("sleepPage");
		
		request.setAttribute("MEMBERINFO", m);
		mav.setViewName("template/sleepPage");
		return mav;
	}
	
	@RequestMapping("/loginCheck")
	public ModelAndView loginCheck(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "loginId") String userId,
			@RequestParam(value = "loginPassword") String password) {
		
		ModelAndView mav = new ModelAndView();
		Login login = new Login(userId, password);
		boolean flag = false;
		
		flag = new MembershipServiceImpl().login(login);
		
		System.out.println(flag);
		
		request.setAttribute("result", Boolean.toString(flag)); 
		mav.setViewName("result");
		return mav;
	}
}

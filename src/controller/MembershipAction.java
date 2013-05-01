package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.MembershipServiceImpl;
import dto.Login;
import dto.Member;


@Controller
public class MembershipAction {

	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="userId") String userId,
																					  	   @RequestParam(value="name") String name,
																					  	   @RequestParam(value="email") String email,
																					  	   @RequestParam(value="password") String password){
		ModelAndView mav = new ModelAndView();
		
		Member member = new Member(userId, email, password, name);
		if(member != null){
			new MembershipServiceImpl().registerMember(member);
			new MembershipServiceImpl().registerMemberInfo(member);
			new MembershipServiceImpl().registerDesign(member);
		}
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/checkId")
	public ModelAndView checkId(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="userId") String userId){
		ModelAndView mav = new ModelAndView();
		boolean flag = false;
		
		flag = new MembershipServiceImpl().checkId(userId);
		
		request.setAttribute("result", Boolean.toString(flag));
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="loginId") String userId,
																						@RequestParam(value="loginPassword")String password){
		ModelAndView mav = new ModelAndView();
		Login login = new Login(userId, password);
		
		boolean flag = new MembershipServiceImpl().login(login);
		if(flag){
			HttpSession session = request.getSession();
			session.setAttribute("MEMBERID", userId);
			session.setAttribute("designType", new MembershipServiceImpl().getDesignType(userId));
			mav.setViewName("main");
		}else{
			request.setAttribute("msg", "로그인 정보가 맞지 않습니다!!");
			mav.setViewName("error/error");
		}
		return mav;
	}
}

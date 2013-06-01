package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.AdminServiceImpl;
import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import util.AdminServer;
import dto.DashboardCount;
import dto.Login;
import dto.Member;

@Controller
public class MobileController {
	
	@RequestMapping("/mobile")
	public ModelAndView go(HttpServletRequest request,
			HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("../../mobile/index"); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
	}
	
	
	@RequestMapping("/mobile_registerForm")
	public ModelAndView mobile_registerForm(HttpServletRequest request,
			HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/mobile_registerForm"); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
	}
	
	@RequestMapping("/mobile_login")
	public ModelAndView mobile_login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "loginId", required=false) String userId,
			@RequestParam(value = "loginPassword", required=false) String password){
		
		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = new JSONArray();
		Login login = new Login(userId, password);
		boolean flag = false;
		
		if(login.getUserId().equals("") || login.getPassword().equals("")){
			System.out.println("아이디 또는 비밀번호를 입력하지 않았습니다.");
			flag = false;
		}else{
			flag = new MembershipServiceImpl().login(login);
		}
		
		 if (flag) {
		 	Member m = new MembershipServiceImpl().getMemberInfo(userId);
			System.out.println(m);
	 		HttpSession session = request.getSession();
	 		session.setAttribute("MEMBERID", userId);
	 		session.setAttribute("MEMBERINFO", m);

	 		session.setAttribute("bookMarkList", new IndividualPageServiceImpl().bookMarkList(userId));
			
			JSONObject memberInfoJ = JSONObject.fromObject(new MembershipServiceImpl().getMemberInfo(userId));
			JSONArray bookmarkListJ = JSONArray.fromObject(new IndividualPageServiceImpl().bookMarkList(userId));
			JSONObject flagJ = JSONObject.fromObject("{flag: true}");
			
			dataJ.add(flagJ);
			dataJ.add(memberInfoJ);
			dataJ.add(bookmarkListJ);
			
			
			System.out.println(dataJ.toString());
			request.setAttribute("result", dataJ.toString());
			mav.setViewName("result");
			
		} else {
			JSONObject flagJ = JSONObject.fromObject("{flag: false}");
			dataJ.add(flagJ);
			request.setAttribute("result", dataJ.toString());
			mav.setViewName("result");
		}
		 
		 return mav;
	}



	@RequestMapping("/mobile_register")
	public ModelAndView mobile_register(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId") String userId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		
		ModelAndView mav = new ModelAndView();
		boolean flag = false;
		
		Member member = new Member(userId, email, password, name);
		
		if(!member.isContentEmpty())
			flag = !new MembershipServiceImpl().checkId(userId);
		
		else{
			flag = false;
		}
		
		if (flag) {
			new MembershipServiceImpl().registerMember(member);
			new MembershipServiceImpl().registerMemberInfo(member);
			new MembershipServiceImpl().registerDesign(member);
			new MembershipServiceImpl().registerRegisterTime(member);
			
			DashboardCount c = new AdminServiceImpl().getDashboardCount();
			JSONObject count = JSONObject.fromObject(c);
			AdminServer.getInstance().pushRegisterMemberCount(count);
			
			request.setAttribute("result", "true");
			
		}else{
			request.setAttribute("result", "false");
		}
		
		mav.setViewName("result");
		return mav;
	}
	
	
	@RequestMapping("/idCheck")
	public ModelAndView idCheck(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId") String userId){
		
		ModelAndView mav = new ModelAndView();
		boolean flag = new MembershipServiceImpl().checkId(userId);
		
		if(flag){ // 이미 아이디가 존재
			request.setAttribute("result", "false");
		}else{ // 가입 가능한 아이디
			request.setAttribute("result", "true");
		}
		
		mav.setViewName("result"); 
		return mav;
	}

}

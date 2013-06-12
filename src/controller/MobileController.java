package controller;

import java.util.ArrayList;
import java.util.Date;

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
import service.FriendshipServiceImpl;
import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import util.AdminServer;
import util.DataEncrypt;
import dao.CategoryDAO;
import dto.BookMark;
import dto.DashboardCount;
import dto.ForBookMarkList;
import dto.Friendship;
import dto.Login;
import dto.Member;
import dto.Message;

@Controller
public class MobileController {
	
	// 모바일 페이지로 이동
	@RequestMapping("/mobile")
	public ModelAndView goMobile(HttpServletRequest request,
			HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("../../mobile/index"); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
	} 
	
	// 모바일 등록 페이지로 이동
	@RequestMapping("/mobile_registerForm")
	public ModelAndView mobile_registerForm(HttpServletRequest request,
			HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobile/mobile_registerForm"); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
	}
	
	// 모바일 페이지에서 로그인 시도
	@RequestMapping("/mobile_login")
	public ModelAndView mobile_login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "loginId", required=false) String userId,
			@RequestParam(value = "loginPassword", required=false) String password){
		
		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = new JSONArray();
		Login login = new Login(userId, new DataEncrypt().encrypt(password));
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

	 		session.setAttribute("bookMarkList", new IndividualPageServiceImpl().bookMarkList(new ForBookMarkList(userId, 0)));
			
			JSONObject memberInfoJ = JSONObject.fromObject(new MembershipServiceImpl().getMemberInfo(userId));
			JSONArray bookmarkListJ = JSONArray.fromObject(new IndividualPageServiceImpl().bookMarkList(new ForBookMarkList(userId, 0)));
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

	// 모바일 페이지에서 로그아웃
	@RequestMapping("/mobile_logout")
	public ModelAndView mobile_logout(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		request.setAttribute("result", "true");
		mav.setViewName("result");
		return mav;
	}

	// 모바일 페이지에서 계정 생성
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
	
	// 모바일 페이지에서 계정 생성할 때 아이디 체크
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
	
	// 모바일 페이지에서 사용자 회원 정보 조회
	@RequestMapping("/mobile_myInfo") 
	public ModelAndView mobile_myInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId") String userId){
		ModelAndView mav = new ModelAndView();
		Member m = new MembershipServiceImpl().getMemberInfo(userId);
		JSONObject jObj = JSONObject.fromObject(m);
		
		request.setAttribute("result", jObj);
		mav.setViewName("result");
		
		return mav;
	}
		
	// 북마크 리스트
	public ArrayList<BookMark> getBookMarkList(int categoryId, String userId){
		return new IndividualPageServiceImpl().bookMarkList(new ForBookMarkList(userId, categoryId));
	}

	// 카테고리 이름
	public String getCategoryName(int categoryId){
		String categoryName = CategoryDAO.getInstance().getCategoryName(categoryId);
		return categoryName;
	}
	
	// 친구 리스트
	public ArrayList<Member> getFriendList(String userId){
		ArrayList<Member> friendList = null;
		Friendship friend = new Friendship(userId, "", "친구");
		friendList = new FriendshipServiceImpl().getFriendList(friend);
		
		return friendList;
	}
	
	// 부모 카테고리의 아이디
	public int getParentId(int categoryId){
		return new IndividualPageServiceImpl().getParentId(categoryId);
	}
	
	// 쪽지 리스트
	public ArrayList<Message> getMessageList(String userId){
		ArrayList<Message> messageList = null;
		
		Message message = new Message(0, userId, "", null, "", new Date(), "", 0, "take");
		
		messageList = new FriendshipServiceImpl().inBox(message);

		return messageList;
	}
}

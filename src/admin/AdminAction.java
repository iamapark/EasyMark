package admin;

import java.util.ArrayList;

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
import util.AdminServer;
import util.SessionManager;
import dto.Count;
import dto.DashboardCount;
import dto.Login;
import dto.Member;
import dto.MemberInfo;

/**
 * 관리자 서비스
 * @author 박진영  
 * */
@Controller
public class AdminAction {
	
	public AdminAction(){
		// Vert.x 서버 가동
		//AdminServer.getInstance().start();
	}
	
	// 관리자 페이지로 이동
	@RequestMapping("/goAdmin")
	public ModelAndView goAdmin(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("admin/index");
		return mav;
	}
	
	// 관리자 페이지에서 로그인할 때 사용
	@RequestMapping("/adminLogin")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="adminId") String adminId,
			@RequestParam(value="password") String password){
		ModelAndView mav = new ModelAndView();
		
		boolean flag = new AdminServiceImpl().login(new Login(adminId, password));
		
		if(flag){ // 로그인 성공
			mav.setViewName("admin/main"); 
		}else{
			request.setAttribute("msg", "아이디와 비밀번호를 정확히 입력하세요. 멍충이 ㅋ");
			mav.setViewName("error/error");
		}
		return mav;
	}
	
	// 회원 테이블을 채우기 위해 회원에 관한 정보가 담긴 리스트를 불러온다.
	@RequestMapping("/fillMemberTable")
	public ModelAndView fillMemberTable(HttpServletRequest request,
										HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Member> memberList = new AdminServiceImpl().fillMemberTable();
		
		JSONArray dataJ = JSONArray.fromObject(memberList);
		
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	// 회원에 관한 상세 정보를 불러온다. (로그인 횟수, 북마크 개수, 이름, 이메일 주소, 이미지 등)
	@RequestMapping("/getMemberInfo_admin")
	public ModelAndView getMemberInfo(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam(value="userId") String userId){

		ModelAndView mav = new ModelAndView();
		MemberInfo m = new AdminServiceImpl().getMemberInfo_admin(userId);

		JSONObject dataJ = JSONObject.fromObject(m);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	// 여러 명 동시에 삭제
	@RequestMapping("/deleteMembers")
	public ModelAndView deleteMembers(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam(value="members") String members){

		ModelAndView mav = new ModelAndView();
		
		ArrayList<String> idList = new ArrayList<String>();
			
		String ar[] = members.split("&");
		
		for(String a:ar){
			idList.add(a.split("=")[1]);
		}
		
		new AdminServiceImpl().deleteMembers(idList);
		
		request.setAttribute("result", "true");
		mav.setViewName("result");
		return mav;
	}
	
	// 한 명 삭제
	@RequestMapping("/deleteMember")
	public ModelAndView deleteMember(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam(value="userId") String userId){

		ModelAndView mav = new ModelAndView();
		
		new AdminServiceImpl().deleteMembers(userId);
		
		request.setAttribute("result", "true");
		mav.setViewName("result");
		return mav;
	}
	
	// 월 별 가입자 수
	@RequestMapping("/getRegisterCount")
	public ModelAndView getRegisterCount(HttpServletRequest request, HttpServletResponse response,
									 @RequestParam(value="month") String selectedMonth){
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Count> c = new AdminServiceImpl().getRegisterCount(selectedMonth);
		
		JSONArray dataJ = JSONArray.fromObject(c);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	// 시간대 별 로그인 수
	@RequestMapping("/getLoginCounterHourly")
	public ModelAndView getLoginCounterHourly(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Count> c = new AdminServiceImpl().getLoginCounterHourly();
		
		JSONArray dataJ = JSONArray.fromObject(c);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	// 관리자 페이지에서 왼쪽 메뉴의 통계를 클릭했을 때 실행되는 메소드
	@RequestMapping("/getTotalStatistics")
	public ModelAndView getTotalStatistics(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Count> c = new AdminServiceImpl().getTotalStatistics();
		
		JSONArray dataJ = JSONArray.fromObject(c);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	// 회원 탈퇴 처리 메소드
	@RequestMapping("/leaveMembership")
	public ModelAndView leaveMembership(HttpServletRequest request, HttpServletResponse response){

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		new AdminServiceImpl().deleteMembers((String)session.getAttribute("MEMBERID"));
		session.invalidate();
		
		mav.setViewName("index");
		return mav;
	}
	
	// 대시보드 상단 네 개의 통계치 메소드
	@RequestMapping("/getDashboardCount")
	public ModelAndView getDashboardCount(HttpServletRequest request, HttpServletResponse response){

		ModelAndView mav = new ModelAndView();
		
		DashboardCount c = new AdminServiceImpl().getDashboardCount();
		
		JSONObject dataJ = JSONObject.fromObject(c);
		dataJ.put("loginMemberCount", SessionManager.getInstance().count());

		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	// 현재 로그인 중인 사용자의 정보를 받아오는 메소드
	@RequestMapping("/getLoginMemberInfoList")
	public ModelAndView getLoginMemberList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		if(SessionManager.getInstance().count() != 0){
			ArrayList<String> ar = SessionManager.getInstance().getLoginId();
			
			ArrayList<Member> loginMemberInfoList = new AdminServiceImpl().getLoginMembersInfoList(ar);
			
			JSONArray dataJ = JSONArray.fromObject(loginMemberInfoList);
			
			request.setAttribute("result", dataJ);
		}else
			request.setAttribute("result", "{'result':'false'}");
		
		
		mav.setViewName("result");
		return mav;
	}
}

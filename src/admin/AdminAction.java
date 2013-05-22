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
import dto.Count;
import dto.Login;
import dto.Member;
import dto.MemberInfo;


@Controller
public class AdminAction {
	
	
	public AdminAction(){
		//AdminServer.getInstance().start();
	}
	
	
	@RequestMapping("/goAdmin")
	public ModelAndView addBookMark(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("admin/index");
		return mav;
	}
			
	
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
			mav.setViewName("error");
		}
		return mav;
	}
	
	
	@RequestMapping("/fillMemberTable")
	public ModelAndView fillMemberTable(HttpServletRequest request,
										HttpServletResponse response){
		System.out.println("fillMemberTable");
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Member> memberList = new AdminServiceImpl().fillMemberTable();
		
		JSONArray dataJ = JSONArray.fromObject(memberList);
		
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
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
	
	@RequestMapping("/getLoginCounterHourly")
	public ModelAndView getLoginCounterHourly(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Count> c = new AdminServiceImpl().getLoginCounterHourly();
		
		JSONArray dataJ = JSONArray.fromObject(c);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/getTotalStatistics")
	public ModelAndView getTotalStatistics(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		
		ArrayList<Count> c = new AdminServiceImpl().getTotalStatistics();
		
		JSONArray dataJ = JSONArray.fromObject(c);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/leaveMembership")
	public ModelAndView leaveMembership(HttpServletRequest request, HttpServletResponse response){

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		new AdminServiceImpl().deleteMembers((String)session.getAttribute("MEMBERID"));
		session.invalidate();
		
		mav.setViewName("index");
		return mav;
	}
	
}

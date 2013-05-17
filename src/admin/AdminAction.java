package admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.AdminServiceImpl;
import dto.Login;
import dto.Member;
import dto.MemberInfo;


@Controller
public class AdminAction {
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
		System.out.println("adminLogin");
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
		System.out.println("회원 수: " + memberList.size());
		
		
		JSONArray dataJ = JSONArray.fromObject(memberList);
		System.out.println(dataJ);
		
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
		System.out.println(dataJ);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	
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

}

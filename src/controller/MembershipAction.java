package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import util.FileWriter;
import dto.Design;
import dto.Img;
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
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="loginId")String userId,
																						@RequestParam(value="loginPassword")String password){
		ModelAndView mav = new ModelAndView();
		Login login = new Login(userId, password);
		
		boolean flag = new MembershipServiceImpl().login(login);
		Member m = new MembershipServiceImpl().getMemberInfo(userId);

		if(flag){
			HttpSession session = request.getSession();
			session.setAttribute("MEMBERID", userId);
			session.setAttribute("designType", new MembershipServiceImpl().getDesignType(userId));
			session.setAttribute("MEMBERINFO", m);
			request.getSession().setAttribute("bookMarkList", new IndividualPageServiceImpl().bookMarkList(userId));
			mav.setViewName("main");
		}else{
			request.setAttribute("msg", "로그인 정보가 맞지 않습니다!!");
			mav.setViewName("error/error");
		}
		return mav;
	}
	
	@RequestMapping("/getMemberInfo")
	public ModelAndView getMemberInfo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		Member m = new MembershipServiceImpl().getMemberInfo((String)session.getAttribute("MEMBERID"));

		JSONObject dataJ = JSONObject.fromObject(m);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/updateMemberInfo")
	public ModelAndView updateMemberInfo(HttpServletRequest request, Img img, @RequestParam(value="file",required=false)MultipartFile file,
																			  @RequestParam(value="setting_name")String name,
																			  @RequestParam(value="setting_email")String email){
		ModelAndView mav = new ModelAndView();
		
		String path = request.getSession().getServletContext().getRealPath("/users/img/")+"/"+(String)request.getSession().getAttribute("MEMBERID")+"/";
		String imgUrl = null;
		if(file != null){
			if(!file.getOriginalFilename().equals("")){
				new FileWriter().writeFile(file, path, file.getOriginalFilename());
				imgUrl = file.getOriginalFilename();
			}
		}
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		Member m = new Member(userId, email, null, name, null, imgUrl);
		new MembershipServiceImpl().updateMemberInfo(m);
		m = new MembershipServiceImpl().getMemberInfo(userId);
		
		JSONObject dataJ = JSONObject.fromObject(m);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/changeDesign")
	public ModelAndView changeDesign(HttpServletRequest request, @RequestParam("design")String design){
		ModelAndView mav = new ModelAndView();
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");

		new MembershipServiceImpl().changeDesign(new Design(userId, design));
		
		request.setAttribute("result", "true");
		mav.setViewName("result");
		return mav;
	}

	
	
	@RequestMapping("/updateBgImg")
	public ModelAndView updateBgImg(HttpServletRequest request, Img img, @RequestParam("backgroundImgFile")MultipartFile file){
		ModelAndView mav = new ModelAndView();
		
		System.out.println(file.getOriginalFilename());
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		String path = request.getSession().getServletContext().getRealPath("/users/img/")+"/"+userId+"/bgImg/";
		String imgUrl = null;
		
		if(!file.getOriginalFilename().equals("")){
			new FileWriter().writeFile(file, path, file.getOriginalFilename());
			imgUrl = "users/img/" + userId + "/bgImg/" + file.getOriginalFilename();
		}
		
		Member m = new Member(userId, imgUrl);
		
		new MembershipServiceImpl().updateBgImg(m);
		
		JSONObject dataJ = JSONObject.fromObject(m);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
	}
	
}

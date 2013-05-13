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
	
	@RequestMapping("/me2dayRegister")
	public ModelAndView me2dayRegister(HttpServletRequest request, HttpServletResponse response){
		// 미투데이 로그인 후 사용자가 '수락합니다' 버튼을 누르면 true,
				// '거절합니다' 버튼을 누르면 false를 리턴
				String result = request.getParameter("result");
				// 미투데이 아이디
				String userId = request.getParameter("user_id");
				// 미투데이 아이디를 식별할 수 있는 고유 키(미투데이에서 제공, 나중에 미투데이 정보를 받아올 때 사용)
				String userKey = request.getParameter("user_key");
				ModelAndView nextPage = new ModelAndView();

				// dto Member 클래스를 사용하여 회원에 관한 정보를 객체에 담아 ibatis에 보낸다.
				// 미투데이 계정으로 가입할 때는 일반 계정 사용자와 구분하기 위해 아이디에 @me2day를 붙인다.
				Member member = new Member(userId + "@me2day", null, null, null, null,
						userKey);

				// 사용자가 '수락합니다' 버튼을 눌렀을 경우
				if (result.equals("true")) {
					nextPage.setViewName("index"); // 시작페이지로 이동한다.

					// registerMe2DayMember 메소드는 인자로 넘겨받은 객체의 아이디가 이미
					// DB에 저장되어 있을 경우(이미 가입되어 있는 회원인 경우 true를 리턴한다.
					if (!new MembershipServiceImpl().registerMe2DayMember(member)) {
						nextPage.setViewName("error/error");
						request.setAttribute("msg", "이미 가입되어 있는 미투데이 계정입니다.");
					}

				} else if (result.equals("false")) { // 사용자가 '거절합니다' 버튼을 누른 경우
					request.setAttribute("msg", "권한 획득에 실패했습니다.");
					nextPage.setViewName("error/error");
				}

				return nextPage;
	}

	@RequestMapping("/me2dayLogin")
	public ModelAndView me2dayLogin(HttpServletRequest request, HttpServletResponse response){
			// 미투데이 로그인 후 사용자가 '수락합니다' 버튼을 누르면 true,
			// '거절합니다' 버튼을 누르면 false를 리턴
			String result = request.getParameter("result");
			// 미투데이 아이디
			String userId = request.getParameter("user_id");
			ModelAndView nextPage = new ModelAndView();

			// 미투데이 계정으로 회원가입할 때 아이디+@me2day로 DB에 저장했기 때문에,
			// Login 클래스의 객체를 생성할 때 아이디에 @me2day를 붙여준다.
			Login login = new Login(userId + "@me2day", null);

			// DB에 아이디가 존재하면 true, 존재하지 않으면 false return
			boolean flag = new MembershipServiceImpl().me2DayLoginCheck(login);

			// '수락합니다' 버튼을 눌렀을 경우
			if (result.equals("true")) {
				System.out.println("flag:"+flag);
				
				if (flag) { // 로그인 성공
					HttpSession session = request.getSession();
					// 로그인 권한을 주기 위해 세션을 설정한다.
					session.setAttribute("MEMBERID", login.getUserId());

					nextPage.setViewName("views/individualPage/bookmark.jsp");

				} else { // 로그인 실패
					request.setAttribute("msg", "미 가입된 미투데이 계정입니다.");
					nextPage.setViewName("views/membership/error.jsp");
				}

			} else if (result.equals("false")) { // '거절합니다' 버튼을 눌렀을 경우
				request.setAttribute("msg", "권한 획득에 실패했습니다.");
				nextPage.setViewName("views/membership/error.jsp");
			}

			return nextPage;																		  	   
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

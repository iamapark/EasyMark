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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import service.AdminServiceImpl;
import service.FriendshipServiceImpl;
import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import util.AdminServer;
import util.DataEncrypt;
import util.FileWriter;
import dto.BookMark;
import dto.Category;
import dto.DashboardCount;
import dto.ForBookMarkList;
import dto.Img;
import dto.Login;
import dto.Member;
import dto.Message;
import exception.LoginException;

@Controller
public class MembershipAction {
	
	private void traffic(){
		AdminServer.getInstance().trafficCount();
	}

	// 일반 계정으로 회원가입하는 메소드
	@RequestMapping("/register")
	public ModelAndView register(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId") String userId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "password") String password) {
		ModelAndView mav = new ModelAndView();
		
		String md5Password = new DataEncrypt().encrypt(password);
		
		Member member = new Member(userId, email, md5Password, name);
		if (member != null) {
			new MembershipServiceImpl().registerMember(member);
			new MembershipServiceImpl().registerMemberInfo(member);
			new MembershipServiceImpl().registerDesign(member);
			new MembershipServiceImpl().registerRegisterTime(member);
			
			DashboardCount c = new AdminServiceImpl().getDashboardCount();
			JSONObject dataJ = JSONObject.fromObject(c);
			AdminServer.getInstance().pushRegisterMemberCount(dataJ);
		}
		mav.setViewName("index");
		
		traffic();
		return mav;
	}

	// 미투데이 계정으로 회원가입하는 메소드
	@RequestMapping("/me2dayRegister")
	public ModelAndView me2dayRegister(HttpServletRequest request,
			HttpServletResponse response) {
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
		Member member = new Member(userId + "@me2day", null, null, null,
				userKey, null, null);

		// 사용자가 '수락합니다' 버튼을 눌렀을 경우
		if (result.equals("true")) {
			nextPage.setViewName("index"); // 시작페이지로 이동한다.

			// registerMe2DayMember 메소드는 인자로 넘겨받은 객체의 아이디가 이미
			// DB에 저장되어 있을 경우(이미 가입되어 있는 회원인 경우 true를 리턴한다.
			if (!new MembershipServiceImpl().registerMe2DayMember(member)) {
				nextPage.setViewName("error/error");
				request.setAttribute("msg", "이미 가입되어 있는 미투데이 계정입니다.");
			}else{
				DashboardCount c = new AdminServiceImpl().getDashboardCount();
				JSONObject dataJ = JSONObject.fromObject(c);
				AdminServer.getInstance().pushRegisterMemberCount(dataJ);
			}

		} else if (result.equals("false")) { // 사용자가 '거절합니다' 버튼을 누른 경우
			request.setAttribute("msg", "권한 획득에 실패했습니다.");
			nextPage.setViewName("error/error");
		}

		traffic();
		return nextPage;
	}

	// 미투데이 계정으로 로그인하는 메소드
	@RequestMapping("/me2dayLogin")
	public ModelAndView me2dayLogin(HttpServletRequest request,
			HttpServletResponse response) {
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
			System.out.println("flag:" + flag);

			if (flag) { // 로그인 성공
				Member m = new MembershipServiceImpl().getMemberInfo(userId + "@me2day");
				
		 		HttpSession session = request.getSession();
		 		session.setAttribute("MEMBERID", userId + "@me2day");
		 		
		 				 		
		 		Member memberKey = new FriendshipServiceImpl().getKey(login.getUserId());
				
				String me2dayKey = null;
				if(memberKey.getMe2DayKey() == null)
					me2dayKey = "NotMe2Login";
				else
					me2dayKey = "Me2Login";	
				
				System.out.println("me2dayKey는? "+me2dayKey);
				
				request.getSession().setAttribute("me2dayKey", me2dayKey);
			 	
				request.setAttribute("designType",
						new MembershipServiceImpl().getDesignType(userId + "@me2day"));
				request.setAttribute("MEMBERINFO", m);

				request.setAttribute("bookMarkList", new IndividualPageServiceImpl().bookMarkList(new ForBookMarkList(userId + "@me2day", 0)));

				Message message = new Message(0, login.getUserId(), "", null, "", new Date(), "", 0, "take");
				ArrayList<Message> newMessage = new FriendshipServiceImpl().messageCount(message);
				int newMessageCount = newMessage.size();
				request.getSession().setAttribute("newMessageCount", newMessageCount);
				
				nextPage.setViewName("main");

			} else { // 로그인 실패
				request.setAttribute("msg", "미 가입된 미투데이 계정입니다.");
				nextPage.setViewName("error/error");
			}

		} else if (result.equals("false")) { // '거절합니다' 버튼을 눌렀을 경우
			request.setAttribute("msg", "권한 획득에 실패했습니다.");
			nextPage.setViewName("error/error");
		}

		traffic();
		return nextPage;
	}

	// 회원가입할 때 사용자가 입력한 아이디가 가입 가능한지 알려주는 메소드
	@RequestMapping("/checkId")
	public ModelAndView checkId(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId") String userId) {
		ModelAndView mav = new ModelAndView();
		boolean flag = false;

		flag = new MembershipServiceImpl().checkId(userId);

		request.setAttribute("result", Boolean.toString(flag));
		mav.setViewName("result");
		return mav;
	}

	// 일반 계정으로 로그인할 때 사용하는 메소드
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "loginId") String userId,
			@RequestParam(value = "loginPassword") String password) throws LoginException {
		
		ModelAndView mav = new ModelAndView();
		String md5LoginPassword = new DataEncrypt().encrypt(password);
		Login login = new Login(userId, md5LoginPassword);
		boolean flag = false;
		
		flag = new MembershipServiceImpl().login(login);
		
		 if (flag) {
	 		HttpSession session = request.getSession();
	 		session.setAttribute("MEMBERID", userId);
	 		
			request.setAttribute("designType",
					new MembershipServiceImpl().getDesignType(userId));
			
			Member m = new MembershipServiceImpl().getMemberInfo(userId);
			request.setAttribute("MEMBERINFO", m);

			Message message = new Message(0, userId, "", null, "", new Date(), "", 0, "take");
			ArrayList<Message> newMessage = new FriendshipServiceImpl().messageCount(message);
			int newMessageCount = newMessage.size();
			request.getSession().setAttribute("newMessageCount", newMessageCount);
			
			request.setAttribute("bookMarkList", new IndividualPageServiceImpl().bookMarkList(new ForBookMarkList(userId, 0)));
			mav.setViewName("main");
		} else {
			request.setAttribute("msg", "로그인 정보가 맞지 않습니다!!");
			mav.setViewName("error/error");
			throw new LoginException();
		}
		 
		traffic();
		return mav;
	}

	// 로그인 후 '회원정보'를 클릭했을 때 회원 정보를 받아오는 메소드
	@RequestMapping("/getMemberInfo")
	public ModelAndView getMemberInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		Member m = new MembershipServiceImpl().getMemberInfo((String) session
				.getAttribute("MEMBERID"));

		JSONObject dataJ = JSONObject.fromObject(m);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}

	// '회원정보' 탭에서 '회원정보 수정' 버튼을 클릭했을 때 실행. 회원에 관한 정보를 업데이트한다.
	@RequestMapping("/updateMemberInfo")
	public ModelAndView updateMemberInfo(HttpServletRequest request, Img img,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "setting_name") String name,
			@RequestParam(value = "setting_email") String email) {
		ModelAndView mav = new ModelAndView();
		String userId = (String) request.getSession().getAttribute("MEMBERID");

		String path = request.getSession().getServletContext()
				.getRealPath("/users/img/")
				+ "/" + userId + "/";
		String imgUrl = null;

		if (file != null) {
			if (!file.getOriginalFilename().equals("")) {
				new FileWriter().writeFile(file, path,
						file.getOriginalFilename());
				imgUrl = file.getOriginalFilename();
				imgUrl = "users/img/" + userId + "/"
						+ file.getOriginalFilename();
			}
		}

		Member m = new Member(userId, email, null, name, null, imgUrl);
		new MembershipServiceImpl().updateMemberInfo(m);
		m = new MembershipServiceImpl().getMemberInfo(userId);

		JSONObject dataJ = JSONObject.fromObject(m);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}
	
	// 사용자의 바탕화면 이미지를 수정할 때 사용하는 메소드
	@RequestMapping("/updateBgImg")
	public ModelAndView updateBgImg(HttpServletRequest request, Img img,
			@RequestParam("backgroundImgFile") MultipartFile file) {
		ModelAndView mav = new ModelAndView();

		System.out.println(file.getOriginalFilename());
		String userId = (String) request.getSession().getAttribute("MEMBERID");
		String path = request.getSession().getServletContext()
				.getRealPath("/users/img/")
				+ "/" + userId + "/bgImg/";
		String imgUrl = null;

		if (!file.getOriginalFilename().equals("")) {
			new FileWriter().writeFile(file, path, file.getOriginalFilename());
			imgUrl = "users/img/" + userId + "/bgImg/"
					+ file.getOriginalFilename();
		}

		Member m = new Member(userId, imgUrl);

		new MembershipServiceImpl().updateBgImg(m);

		JSONObject dataJ = JSONObject.fromObject(m);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}

	//ExtensionEasyMark에서 사용하는 메소드. 로그인 시 사용됨.
	@RequestMapping("/extensionUserCheck")
	public ModelAndView extensionUserCheck(HttpServletRequest request,
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam("callback") String callback) {
		ModelAndView mav = new ModelAndView();

		boolean flag = new MembershipServiceImpl().login(new Login(userId,
				password));

		String data = callback + "({\"result\" : \"" + Boolean.toString(flag)
				+ "\", \"userId\" : \"" + userId + "\"})";

		request.setAttribute("result", data);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}

	// 회원이 추가한 북마크 리스트를 받아오는 메소드
	@RequestMapping("/viewBookMarkList")
	public ModelAndView viewBookMarkList(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView nextPage = new ModelAndView();

		String userId = (String) request.getSession().getAttribute("MEMBERID");
		System.out.println("userId" + userId);
		ArrayList<BookMark> viewBookMarkList = null;

		viewBookMarkList = new MembershipServiceImpl().viewBookMarkList(userId);
		
		JSONArray viewBook = JSONArray.fromObject(viewBookMarkList);

		request.setAttribute("result", viewBook);

		nextPage.setViewName("result");
		System.out.println("viewBookMarkList" + viewBookMarkList);
		
		traffic();
		return nextPage;

	}
	
	// '로그 아웃' 버튼을 누르면 실행되는 메소드
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView nextPage = new ModelAndView();
		
		request.getSession().invalidate();
		nextPage.setViewName("index");
		
		traffic();
		return nextPage;

	}
	
	// 세션이 남아있는 상태에서 사용자가 EasyMark 페이지에 접속할 때 로그인 과정을 거치지 않고 바로 메인 페이지로 이동시킨다. 
	@RequestMapping("/loginSession")
	public ModelAndView loginSession(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView mav = new ModelAndView();
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		
		System.out.println("로그인 아이디: " + request.getSession().getAttribute("MEMBERID"));
		
		 if (userId != null) {
		 	Member m = new MembershipServiceImpl().getMemberInfo(userId);
		
			request.setAttribute("designType",
					new MembershipServiceImpl().getDesignType(userId));
			request.setAttribute("MEMBERINFO", m);
			
			request.setAttribute("bookMarkList", new IndividualPageServiceImpl().bookMarkList(new ForBookMarkList(userId, 0)));
			//bookMar add 할때 categoryList option 가져오기
			request.setAttribute("categoryList", new IndividualPageServiceImpl().categoryList(userId));
			
			mav.setViewName("main");
		} else {
			request.setAttribute("msg", "로그인 정보가 맞지 않습니다!!");
			mav.setViewName("error/error");
		}
		 
		 traffic();
		return mav;
	}
	
	// 사용자의 카테고리 리스트를 트리 구조로 가져오는 메소드
	@RequestMapping("/getCategoryTree")
	public ModelAndView getCategoryTree(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("MEMBERID");
		
		ArrayList<Category> categoryList = new IndividualPageServiceImpl().getCategoryList(userId);
		
		request.setAttribute("result", getCategoryTree(categoryList).toString());
		mav.setViewName("result");
		return mav;
	}
	
	// getCategoryTree에서 사용
	// ArrayList<Category> 형식의 데이터를 JsonObject(트리 구조) 형식으로 변환한다.
	private static JsonObject getJsonObject(ArrayList<Category> categoryList, int categoryId){
		
		JsonObject result = new JsonObject();
		
		if(categoryId == 0){
			result.putString("categoryName", "바탕화면");
			result.putNumber("categoryId", 0);
		}else{
			Category c = getCategory(categoryList, categoryId);
			result.putString("categoryName", c.getCategoryName());
			result.putNumber("categoryId", c.getCategoryId());
			result.putNumber("parentId", c.getParentCategoryId());
		}
		
		if(isParentIdExist(categoryList, categoryId))
			result.putElement("children", getJsonArray(categoryList, categoryId));
		
		return result;
	}
	
	// getCategoryTree에서 사용
	private static JsonArray getJsonArray(ArrayList<Category> categoryList, int parentId){
		
		JsonArray result = new JsonArray();
		ArrayList<Category> count = getParentCategoryCount(categoryList, parentId);
		
		for(int i=0; i<count.size(); i++){
			result.addObject(getJsonObject(categoryList, count.get(i).getCategoryId()));
		}
		
		return result;
	}
	
	// getCategoryTree에서 사용
	private static ArrayList<Category> getParentCategoryCount(ArrayList<Category> categoryList, int parentId){
		ArrayList<Category> result = new ArrayList<Category>();
		
		for(Category c: categoryList){
			if(parentId == c.getParentCategoryId())
				result.add(c);
		}
		
		return result;
	}
	
	// 카테고리 리스트 중에서 인덱스에 해당하는 카테고리를 반환하는 메소드
	public static Category getCategory(ArrayList<Category> categoryList, int index){
		Category result = null;
		
		for(Category c: categoryList){
			if(index == c.getCategoryId())
				result = c;
		}
		
		return result;
	}
	
	// getCategoryTree에서 사용
	private static boolean isParentIdExist(ArrayList<Category> categoryList, int parentId){
		boolean result = false;
		
		for(Category c: categoryList){
			if(c.getParentCategoryId() == parentId){
				result = true;
			}
		}
		
		return result;
	}
	
	// 카테고리 리스트를 트리 구조로 반환하는 메소드. 
	public static JsonObject getCategoryTree(ArrayList<Category> categoryList){
		JsonObject jData = getJsonObject(categoryList, 0);
		
		return jData;
	}

	
	@RequestMapping("/sleepPage")
	public ModelAndView sleepPage(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("MEMBERID");
		session.invalidate();
		Member m = new MembershipServiceImpl().getMemberInfo(userId);

		request.setAttribute("MEMBERINFO", m);
		mav.setViewName("template/sleepPage");
		return mav;
	}
	
	@RequestMapping("/loginCheck")
	public ModelAndView loginCheck(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "loginId") String userId,
			@RequestParam(value = "loginPassword") String password) {
		String md5Password = new DataEncrypt().encrypt(password);
		ModelAndView mav = new ModelAndView();
		Login login = new Login(userId, md5Password);
		boolean flag = false;
		
		flag = new MembershipServiceImpl().login(login);
		
		System.out.println(flag);
		
		request.setAttribute("result", Boolean.toString(flag)); 
		mav.setViewName("result");
		return mav;
	}
	
}

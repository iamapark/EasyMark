package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.FriendshipServiceImpl;
import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import util.AdminServer;
import util.FileWriter;
import dto.BookMark;
import dto.Category;
import dto.Design;
import dto.Friendship;
import dto.Img;
import dto.Member;

@Controller
public class IndividualPageAction {
	
	private void traffic(){
		AdminServer.getInstance().trafficCount();
	}

	@RequestMapping("/addMark")
	public ModelAndView addBookMark(
			HttpServletRequest request,
			Img img,
			@RequestParam(value = "addBookMarkImage", required = false) MultipartFile file,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "url") String url,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "category") String category,
			@RequestParam(value = "userId", required = false) String userId) throws UnsupportedEncodingException {
		System.out.println("addMark()!!");


		System.out.println("userId: " + userId);

		ModelAndView nextPage = new ModelAndView();
		if (userId == null)
			userId = (String) request.getSession().getAttribute("MEMBERID");
		String imgUrl = null;
		System.out.println("userID :" + userId);

		// 사용자가 입력한 URL 의 앞부분이 http:// or https://로 시작하지 않을 경우
		// 앞부분에 붙여준다.
		if (!url.trim().matches("^https?://[a-zA-Z0-9./?&_=!#&%+--,]*$")) {
			url = "http://" + url;
		}

		// 이미지가 저장되는 경로
		String path = request.getSession().getServletContext()
				.getRealPath("/users/img/")
				+ "/" + userId + "/bookmark/";
		System.out.println(path);

		if (file != null) {
			if (!file.getOriginalFilename().equals("")) {
				new FileWriter().writeFile(file, path,
						file.getOriginalFilename());
				imgUrl = "users/img/" + userId + "/bookmark/"
						+ file.getOriginalFilename();
			} else {
				imgUrl = "images/Bookmark.png";
			}
		} else {
			imgUrl = "images/Bookmark.png";
		}

		// 사용자ID 가져오기
		String status = "false";
		int posx = 0;
		int posy = 0;

		ArrayList<BookMark> bookMarkList = null;
		// 현재 사용자의 북마크 리스트 가져오기
		bookMarkList = new IndividualPageServiceImpl().bookMarkList(userId);

		// 만약 처음 북마크 추가이면 1,1 위치 삽입
		if (bookMarkList.size() == 0) {
			posx = 1;
			posy = 1;
		} else {// //////////추가한 아이콘 제일 마지막 아이콘 옆에 배치!!
			posx = new IndividualPageServiceImpl().bookMarkPosx(userId);
			System.out.println("x=" + posx);
			// ParameterClass 2개라서 HashMap 이용 맞나?
			HashMap<String, Object> pos = new HashMap<String, Object>();
			pos.put("userId", userId);
			pos.put("posX", posx);
			posy = new IndividualPageServiceImpl().bookMarkPosy(pos); // x줄에 제일
																		// 마지막
																		// y값
			System.out.println("by=" + posy);
			posy++; // +1해서 다음에 놓을 곳 배치
			System.out.println("ay=" + posy);

			if (posy == 7) {// 다음줄로 넘기기
				posx++;
				posy = 1;
			}

		}

		BookMark bookMark = new BookMark(0, URLDecoder.decode(name, "utf-8"), url, description, userId,
				status, posx, posy, imgUrl, 0, category);

		try {
			name = URLDecoder.decode(name, "utf-8");
			description = URLDecoder.decode(description, "utf-8");
		} catch (UnsupportedEncodingException e) {

		}

		int maxBookmarkId = new IndividualPageServiceImpl()
				.addBookMark(bookMark);
		bookMarkList = new IndividualPageServiceImpl().bookMarkList(userId);
		request.getSession().setAttribute("bookMarkList", bookMarkList);

		Member m = new MembershipServiceImpl().getMemberInfo(userId);
		request.getSession().setAttribute("MEMBERINFO", m);

		JSONObject jobj = new JSONObject();
		jobj.put("x", posx);
		jobj.put("y", posy);
		jobj.put("id", maxBookmarkId);
		jobj.put("imgUrl", imgUrl);
		jobj.put("url", url);

		request.setAttribute("result", jobj);
		nextPage.setViewName("result");

		traffic();
		return nextPage;
	}

	@RequestMapping("/getBookMarkList")
	public ModelAndView getBookMarkList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView nextPage = new ModelAndView();

		// 현재 사용자의 북마크 리스트 가져오기
		ArrayList<BookMark> bookMarkList = new IndividualPageServiceImpl()
				.bookMarkList((String) request.getSession().getAttribute(
						"MEMBERID"));

		JSONArray dataJ = JSONArray.fromObject(bookMarkList);
		request.setAttribute("result", dataJ);
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}

	// 북마크 수정 탭을 눌렀을 때 호출되는 메소드. 북마크에 관한 정보를 가져온다.
	@RequestMapping("/getBookmarkInfo")
	public ModelAndView getBookmarkInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView nextPage = new ModelAndView();
		BookMark bookMark = new BookMark();
		int bookMarkId = Integer.parseInt(request.getParameter("bookMarkId"));

		bookMark = new IndividualPageServiceImpl().getBookMark(bookMarkId);

		JSONArray bookMarkJ = JSONArray.fromObject(bookMark);
		request.setAttribute("result", bookMarkJ.toString());
		nextPage.setViewName("result");

		traffic();
		return nextPage;
	}

	// 북마크 추천을 눌렀을 때 호출되는 메소드. 북마크에 관한 정보와 로그인 중인 사용자의 친구 리스트를 가져온다.
	@RequestMapping("/getRecommendInfo")
	public ModelAndView getRecommendInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView nextPage = new ModelAndView();
		BookMark bookMark = new BookMark();
		
		int bookMarkId = Integer.parseInt(request.getParameter("bookMarkId"));
		String userId = (String) request.getSession().getAttribute("MEMBERID");
		
		bookMark = new IndividualPageServiceImpl().getBookMark(bookMarkId);
		Friendship friend = new Friendship(userId, "", "친구");
		ArrayList<Member> friendList = new FriendshipServiceImpl().getFriendList(friend);
		
		JSONArray bookMarkJ = JSONArray.fromObject(bookMark);
		JSONArray friendListJ = JSONArray.fromObject(friendList);
		JSONArray dataJ  = new JSONArray();
		dataJ.add(bookMarkJ);
		dataJ.add(friendListJ);
		
		String data = "{" + bookMarkJ.toString() + ", " + friendListJ.toString() + "}";
		System.out.println(dataJ.toString());
		System.out.println(data);
		
		request.setAttribute("result", dataJ.toString());
		nextPage.setViewName("result");

		traffic();
		return nextPage;
	}
	
	@RequestMapping("/modifyMark")
	public ModelAndView modifymark(
			HttpServletRequest request,
			Img img,
			@RequestParam(value = "modifyBookmarkImage", required = false) MultipartFile file,
			@RequestParam(value = "modifyBookmarkName") String name,
			@RequestParam(value = "modifyBookmarkUrl") String url,
			@RequestParam(value = "modifyBookmarkDescription") String desc,
			@RequestParam(value = "bookmarkId") int bookMarkId) {

		System.out.println("name: " + name);
		System.out.println("url: " + url);
		System.out.println("desc: " + desc);

		ModelAndView nextPage = new ModelAndView();

		String userId = (String) request.getSession().getAttribute("MEMBERID");
		String imgUrl = null;

		if (file != null) {
			if (!file.getOriginalFilename().equals("")) {
				String path = request.getSession().getServletContext()
						.getRealPath("/users/img/")
						+ "/" + userId + "/bookmark/";
				new FileWriter().writeFile(file, path,
						file.getOriginalFilename());
				imgUrl = "users/img/" + userId + "/bookmark/"
						+ file.getOriginalFilename();
			}
		}

		BookMark bookMark = new BookMark(bookMarkId, name, url, desc, "", "",
				0, 0, imgUrl, 0, "");
		new IndividualPageServiceImpl().modifyMark(bookMark);

		JSONObject jobj = new JSONObject();
		jobj.put("imgUrl", imgUrl);
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}

	@RequestMapping("/deleteMark")
	public ModelAndView deleteMark(HttpServletRequest request,
			HttpServletResponse response, Img img,
			@RequestParam(value = "bookmarkId") int bookMarkId) {
		ModelAndView nextPage = new ModelAndView();

		BookMark bookmark=null;
		//만약 category 삭제하는 거면 category Table에서도 제거
		bookmark=new IndividualPageServiceImpl().getBookMark(bookMarkId);
		if(bookmark.getCategory().equals("")){
			//category 삭제
			new IndividualPageServiceImpl().deleteCategory(bookmark);
		}

		new IndividualPageServiceImpl().deleteIcon(bookMarkId);

		request.setAttribute("result", Boolean.toString(true));
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
	
	// 북마크 한 번에 여러 개 지울 때
	@RequestMapping("/deleteBookMarks")
	public ModelAndView deleteMarks(HttpServletRequest request,
			HttpServletResponse response, Img img,
			@RequestParam(value = "bookmarks") String bookmarks) {
		
		ModelAndView nextPage = new ModelAndView();
		
		ArrayList<Integer> selectedIdList = new ArrayList<Integer>();
		String[] selectedBookmarkId = bookmarks.split(",");
		
		String ar[] = bookmarks.split("&");
		
		for(String a:ar){
			selectedIdList.add(Integer.parseInt(a.split("=")[1]));
			System.out.println("id: " + a.split("=")[1]);
		}
		
		
		/*for(String selectedId: selectedBookmarkId){
			selectedIdList.add(Integer.parseInt(selectedId));
			System.out.println("selectedId: " + selectedId);
		}*/
		
		new IndividualPageServiceImpl().deleteIcons(selectedIdList);
		
		request.setAttribute("result", Boolean.toString(true));
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
	

	@RequestMapping("/arrange")
	public ModelAndView ararrangerange(HttpServletRequest request,
			@RequestParam(value = "location") String location) {
		ModelAndView nextPage = new ModelAndView();
		System.out.println("location: " + location);
		JSONObject json = (JSONObject) JSONSerializer.toJSON(location);

		JSONObject result = null;

		for (int i = 0; i < json.size(); i++) {
			result = (JSONObject) json.get(String.valueOf(i));

			int bookMarkId = Integer.parseInt(result.get("id").toString());
			int posX = Integer.parseInt(result.get("row").toString());
			int posY = Integer.parseInt(result.get("col").toString());
			BookMark bookMark = new BookMark(bookMarkId, "", "", "", "", "",
					posX, posY);
			new IndividualPageServiceImpl().arrangeIcon(bookMark);
		}

		JSONObject jobj = new JSONObject();
		jobj.put("location", location);

		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}

	@RequestMapping("/getAddBookMarkInfo")
	public ModelAndView getAddBookMarkInfo(HttpServletRequest request,
			@RequestParam(value = "url") String url) {
		ModelAndView nextPage = new ModelAndView();

		boolean flag = false;
		String title = null;
		URL addBookmarkUrl = null;

		// 사용자가 입력한 URL 의 앞부분이 http:// or https://로 시작하지 않을 경우
		// 앞부분에 붙여준다.
		if (!url.trim().matches("^https?://[a-zA-Z0-9./?&_=!#&]*$")) {
			url = "http://" + url;
		}

		try {
			addBookmarkUrl = new URL(url);

			InputStream inn = addBookmarkUrl.openConnection().getInputStream(); // ★URL객체에서
																				// 커넥션을
																				// 열고
																				// 스트림
																				// 객체를
																				// 반환받는다
			BufferedReader br = new BufferedReader(new InputStreamReader(inn));

			String temp = null;
			String html = null;
			while ((temp = br.readLine()) != null) {
				html += temp;
			}
			br.close();

			Pattern p = Pattern.compile("<title>(.*?)</title>");
			Matcher m = p.matcher(html);

			while (m.find()) {
				title = m.group();
			}

			title = title.replace("<title>", "");
			title = title.replace("</title>", "");

			flag = true;
		} catch (MalformedURLException e) {
			System.out.println("url이 잘못되었습니다.");
		} catch (IOException e) {
			System.out.println("IOException");
		}

		System.out.println("끝");
		JSONObject jobj = new JSONObject();
		jobj.put("title", title);
		jobj.put("flag", Boolean.toString(flag));

		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}


	@RequestMapping("/extensionAddMark")
	public ModelAndView extensionAddMark(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "url") String url,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "desc", required = false) String desc) {
		ModelAndView mav = new ModelAndView();

		// 사용자가 입력한 URL 의 앞부분이 http:// or https://로 시작하지 않을 경우
		// 앞부분에 붙여준다.
		if (!url.trim().matches("^https?://[a-zA-Z0-9./?&_=]*$")) {
			url = "http://" + url;
		}

		System.out.println("extensionAddMark");
		System.out.println("url: " + url);
		System.out.println("name: " + name);
		System.out.println("desc: " + desc);

		request.setAttribute("result", "true");
		mav.setViewName("result");
		
		traffic();
		return mav;
	}

	@RequestMapping("/extensionAddMark")
	public ModelAndView extensionAddMark(
			HttpServletRequest request,
			Img img,
			@RequestParam(value = "addBookMarkImage", required = false) MultipartFile file,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "url") String url,
			@RequestParam(value = "description") String description,
			@RequestParam(value = "category") String category,
			@RequestParam(value = "userId") String userId) {

		ModelAndView nextPage = new ModelAndView();

		System.out.println("extensionAddMark()");
		System.out.println("userId: " + userId);

		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}

	//카테고리 추가
	//isExistCategory 써서 중복된 category add 안되게 해야한다
	@RequestMapping("/addCategory")
	public ModelAndView addCategory(HttpServletRequest request,@RequestParam(value="categoryName") String categoryName, 
															   @RequestParam(value="userId",required=false)String userId) throws UnsupportedEncodingException{
		System.out.println("addCategory()!!");
		ModelAndView nextPage = new ModelAndView();
		if(userId == null)
			userId = (String)request.getSession().getAttribute("MEMBERID");
		String imgUrl = "images/folder.png";
		System.out.println("userID :"+userId);
		System.out.println("categoryName: " + URLDecoder.decode(categoryName, "utf-8"));
		String status="false";
		int posx=0;
		int posy=0;
		ArrayList<BookMark> bookMarkList = null;
		//현재 사용자의 북마크 리스트 가져오기
		bookMarkList = new IndividualPageServiceImpl().bookMarkList(userId);
		
		//만약 처음 북마크 추가이면 1,1 위치 삽입
		if(bookMarkList.size()==0){
			posx=1;
			posy=1;
		}else{////////////추가한 아이콘 제일 마지막 아이콘 옆에 배치!!
			posx=new IndividualPageServiceImpl().bookMarkPosx(userId);
			System.out.println("x="+posx);
			//ParameterClass 2개라서 HashMap 이용 맞나?
			HashMap<String, Object> pos=new HashMap<String, Object>();
			pos.put("userId", userId);
			pos.put("posX", posx);
			posy=new IndividualPageServiceImpl().bookMarkPosy(pos); //x줄에 제일 마지막 y값
			System.out.println("by="+posy);
			posy++; //+1해서 다음에 놓을 곳 배치
			System.out.println("ay="+posy);
			if(posy==7){//다음줄로 넘기기
				posx++;
				posy=1;
			}
			
		}
		

		BookMark bookMark = new BookMark(0,URLDecoder.decode(categoryName, "utf-8"), "", "", userId, status, posx, posy, imgUrl, 0,"");
		Category category=new Category(0, URLDecoder.decode(categoryName, "utf-8"), userId);
		//category폴더 bookmark Table에 저장
		int maxBookmarkId = new IndividualPageServiceImpl().addBookMark(bookMark);
		//User에 대한 category 내용 category Table에 저장
		new IndividualPageServiceImpl().addCategory(category);
		JSONObject jobj = new JSONObject();
		jobj.put("x", posx);
		jobj.put("y", posy);
		jobj.put("id", maxBookmarkId);
		jobj.put("imgUrl", imgUrl);
		jobj.put("categoryName", categoryName);
		
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
	
	// 사용자가 북마크 아이콘을 더블 클릭하여 즐겨찾기 URL 페이지에 접속할 경우 해당 북마크 frequency를 +1 한다.
	@RequestMapping("/increaseFrequency")
	public ModelAndView increaseFrequency(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "bookmarkId") String bookmarkId) {

		ModelAndView nextPage = new ModelAndView();

		new IndividualPageServiceImpl().increaseFrequency(bookmarkId);
		
		request.setAttribute("result", "true");
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
}

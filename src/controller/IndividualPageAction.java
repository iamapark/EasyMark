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
import javax.servlet.http.HttpSession;

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
import util.AdminServer;
import util.FileWriter;
import dto.BookMark;
import dto.Category;
import dto.ForBookMarkList;
import dto.Friendship;
import dto.Img;
import dto.Member;
import dto.Position;

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
			@RequestParam(value = "category") int categoryId,
			@RequestParam(value = "userId", required = false) String userId) throws UnsupportedEncodingException {

		System.out.println("addMark()!!");

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
		String status = "bookmark";
		
		ArrayList<Position> currentPosition = new ArrayList<Position>();
		if(!(categoryId == 0)){ // 바탕화면이 아닐 경우 1,1 좌표는 비워둔다. 상위 카테고리로 올라가는 아이콘을 배치해야하기 때문임.
			currentPosition.add(new Position(1, 1));
		}

		Category categoryPos = new Category();
		categoryPos.setCategoryId(categoryId);
		categoryPos.setUserId(userId);
		ArrayList<Position> d = new IndividualPageServiceImpl().bookMarkPos(categoryPos);
		
		currentPosition.addAll(d);

		// 아이콘이 추가될 x,y 좌표를 받아온다.
		Position newPosition = getPosition(currentPosition, 8, 4);
		System.out.println("newPosition");
		System.out.println(newPosition);
		
		BookMark bookMark = new BookMark(0, URLDecoder.decode(name, "utf-8"), url, description, userId,
				status, newPosition.getPosX(), newPosition.getPosY(), imgUrl, 0, String.valueOf(categoryId));

		try {
			name = URLDecoder.decode(name, "utf-8");
			description = URLDecoder.decode(description, "utf-8");
		} catch (UnsupportedEncodingException e) {

		}

		int maxBookmarkId = new IndividualPageServiceImpl()
				.addBookMark(bookMark);

		JSONObject jobj = new JSONObject();
		jobj.put("x", newPosition.getPosX());
		jobj.put("y", newPosition.getPosY());
		jobj.put("id", maxBookmarkId);
		jobj.put("imgUrl", imgUrl);
		jobj.put("url", url);

		request.setAttribute("result", jobj);
		nextPage.setViewName("result");

		traffic();
		return nextPage;
	}
	
	//아이콘을 배치할 x, y 좌표를  결정한다.
	public static Position getPosition(ArrayList<Position> currentPosition, int rowLength, int colLength){
		Position newPosition = new Position();
		ArrayList<Integer> posYList = new ArrayList<Integer>();
		
		for(int col=1; col<=colLength; col++){
			for(int i=0; i<currentPosition.size(); i++){
				if(currentPosition.get(i).getPosX() == col)
					posYList.add(currentPosition.get(i).getPosY());
			}
		
			for(int row=1; row<=rowLength; row++){
				if(!posYList.contains(row)){

					newPosition.setPosX(col);
					newPosition.setPosY(row);
					return newPosition;
				}
					
			}
			
			posYList.clear();
		}
		
		
		return newPosition;
	}
/*
	@RequestMapping("/getBookMarkList")
	public ModelAndView getBookMarkList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "userId", required=false) String userId) {
		
		ModelAndView nextPage = new ModelAndView();
		
		if(userId == null){
			userId = (String) request.getSession().getAttribute("MEMBERID");
		}
		
		// 현재 사용자의 북마크 리스트 가져오기
		ArrayList<BookMark> bookMarkList = new IndividualPageServiceImpl()
				.bookMarkList(userId);

		JSONArray dataJ = JSONArray.fromObject(bookMarkList);
		request.setAttribute("result", dataJ);
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
*/
	// 북마크 수정 탭을 눌렀을 때 호출되는 메소드. 북마크에 관한 정보를 가져온다.
	@RequestMapping("/getBookmarkInfo")
	public ModelAndView getBookmarkInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView nextPage = new ModelAndView();
		BookMark bookMark = new BookMark();
		int bookMarkId = Integer.parseInt(request.getParameter("bookMarkId"));

		bookMark = new IndividualPageServiceImpl().getBookMark(bookMarkId);
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("MEMBERID");
		
		ArrayList<Category> categoryList = new IndividualPageServiceImpl().getCategoryList(userId);
		
		JSONArray bookMarkJ = JSONArray.fromObject(bookMark);
		bookMarkJ.add(MembershipAction.getCategoryTree(categoryList).toString());
		
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
			@RequestParam(value = "modifyBookmarkUrl", required = false) String url,
			@RequestParam(value = "modifyBookmarkDescription", required = false) String desc,
			@RequestParam(value = "bookmarkId") int bookMarkId,
			@RequestParam(value = "categoryId") int categoryId,
			@RequestParam(value = "flag") boolean flag) {

		ModelAndView nextPage = new ModelAndView();
		Position newPosition = new Position();
		
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
		
		// flag=true, 북마크의 현재 카테고리와 선택된 카테고리가 같을 때, 좌표를 수정해서는 안됨
		if(!flag){
			
			ArrayList<Position> currentPosition = new ArrayList<Position>();
			if(categoryId != 0){ // 바탕화면이 아닐 경우 1,1 좌표는 비워둔다. 상위 카테고리로 올라가는 아이콘을 배치해야하기 때문임.
				currentPosition.add(new Position(1, 1));
			}
			
			Category category = new Category();
			category.setCategoryId(categoryId);
			category.setUserId(userId);

			ArrayList<Position> d = new IndividualPageServiceImpl().bookMarkPos(category);
			
			currentPosition.addAll(d);

			// 아이콘이 추가될 x,y 좌표를 받아온다.
			newPosition = getPosition(currentPosition, 8, 4);

		}else{
			newPosition.setPosX(0);
			newPosition.setPosY(0);
		}
		
		BookMark bookMark = new BookMark(bookMarkId, name, url, desc, "", "",
				newPosition.getPosX(), newPosition.getPosY(), imgUrl, 0, String.valueOf(categoryId));
		new IndividualPageServiceImpl().modifyMark(bookMark);

		JSONObject jobj = new JSONObject();
		jobj.put("imgUrl", imgUrl);
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
	
	@RequestMapping("/modifyCategory")
	public ModelAndView modifyCategory(
			HttpServletRequest request,
			@RequestParam(value = "modifyBookmarkName") String name,
			@RequestParam(value = "bookmarkId") int bookMarkId,
			@RequestParam(value = "categoryId") int categoryId) {
		
		ModelAndView nextPage = new ModelAndView();
		
		// bookmark 테이블 수정
		BookMark bookMark = new BookMark(bookMarkId, name, String.valueOf(categoryId));
		new IndividualPageServiceImpl().modifyMark(bookMark);
		
		// bookmark_category 테이블 수정
		Category category = new Category(categoryId, name);
		new IndividualPageServiceImpl().modifyCategory(category);
		
		request.setAttribute("result", "true");
		nextPage.setViewName("result");
		return nextPage;
	}

	@RequestMapping("/deleteMark")
	public ModelAndView deleteMark(HttpServletRequest request,
			HttpServletResponse response, Img img,
			@RequestParam(value = "bookmarkId") int bookMarkId) {
		ModelAndView nextPage = new ModelAndView();

		new IndividualPageServiceImpl().deleteIcon(bookMarkId);

		request.setAttribute("result", Boolean.toString(true));
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
	
	@RequestMapping("/deleteCategory")
	public ModelAndView deleteCategory(HttpServletRequest request,
			HttpServletResponse response, Img img,
			@RequestParam(value = "categoryId") int categoryId) {
		ModelAndView nextPage = new ModelAndView();
		ArrayList<Integer> deleteTargetList = new ArrayList<Integer>();
		
		
		
		System.out.println("categoryId: " + categoryId);
		
		deleteTargetList.add(categoryId);
		getDeleteTargetList(categoryId, deleteTargetList, "bookmarkCategory");
		// bookmark_category에서 리스트에 있는 아이디가 category_id와 일치하는 데이터 지운다.
		// bookmark에서 리스트에 있는 아이디가 category와 일치하는 데이터를 지운다.
		new IndividualPageServiceImpl().deleteBookMarkCategory(deleteTargetList);
		
		for(int i: deleteTargetList){
			System.out.println(i);
		}
		
		request.setAttribute("result", Boolean.toString(true));
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
	
	private void getDeleteTargetList(int categoryId,
			ArrayList<Integer> deleteTargetList, String target) {
		
		ArrayList<Integer> targetList = new IndividualPageServiceImpl().getDeleteTargetList(target, categoryId);
		
		for(int i=0; i<targetList.size(); i++){
			getDeleteTargetList(targetList.get(i), deleteTargetList, target);
			deleteTargetList.add(targetList.get(i));
		}

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
			
			System.out.println("id: " + result.get("id") + ", row: " + result.get("row") + ", col: " + result.get("col"));
			
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
			
			if(title != null){
				title = title.replace("<title>", "");
				title = title.replace("</title>", "");
			}

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

	
	
	// 사용자가 북마크 아이콘을 더블 클릭하여 즐겨찾기 URL 페이지에 접속할 경우 해당 북마크 frequency를 +1 한다.
	@RequestMapping("/increaseFrequency")
	public ModelAndView increaseFrequency(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "bookmarkId") String bookmarkId) {

		ModelAndView nextPage = new ModelAndView();
		System.out.println("bookmarkId: " + bookmarkId);
		new IndividualPageServiceImpl().increaseFrequency(bookmarkId);
		
		request.setAttribute("result", "true");
		nextPage.setViewName("result");
		
		traffic();
		return nextPage;
	}
	//카테고리 추가할 때 겹치면 추가 안함
	@RequestMapping("/isExistCategory")
	public ModelAndView isExistCategory(HttpServletRequest request,@RequestParam(value="categoryName") String categoryName){
		System.out.println("isExistCategory()");
		ModelAndView nextPage = new ModelAndView();
		boolean flag=false;
		String userId=(String)request.getSession().getAttribute("MEMBERID");
		HashMap<String, Object> categoryInfo = new HashMap<String, Object>();
		categoryInfo.put("userId", userId);
		categoryInfo.put("categoryName", categoryName);
		flag=new IndividualPageServiceImpl().isExistCategory(categoryInfo);
		System.out.println("categoryName : "+categoryName);
		System.out.println("flag : "+flag);
		JSONObject jobj = new JSONObject();
		jobj.put("flag", flag);
		
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		return nextPage;
	}

	@RequestMapping("/categoryOptionUpdate")
	public ModelAndView categoryUpdate(HttpServletRequest request,HttpServletResponse response){
		ModelAndView nextPage = new ModelAndView();
		/*String userId=(String)request.getSession().getAttribute("MEMBERID");
		ArrayList<Category> categoryList=new ArrayList<>();
		categoryList=new IndividualPageServiceImpl().categoryList(userId);
		JSONArray dataJ = JSONArray.fromObject(categoryList);
		request.setAttribute("result",dataJ);
		nextPage.setViewName("result");*/
		return nextPage;
	}
	
	//카테고리 더블클릭시 해당하는 북마크 리스트 가져오기
	@RequestMapping("/viewCategory")
	public ModelAndView viewCategory(HttpServletRequest request,@RequestParam(value="categoryId") int categoryId){
		ModelAndView nextPage = new ModelAndView();
		ArrayList<BookMark> list=null;
		String userId=(String)request.getSession().getAttribute("MEMBERID");
		
		JSONObject dataJ = new JSONObject();
		list = new IndividualPageServiceImpl().bookMarkList(new ForBookMarkList(userId, categoryId));
		JSONArray listJ = JSONArray.fromObject(list);
		
		dataJ.put("list", listJ);
		
		if(categoryId != 0){
			int parentId = new IndividualPageServiceImpl().getParentId(categoryId);
			dataJ.put("parentId", parentId);
		}
		
		request.setAttribute("result", dataJ);
		nextPage.setViewName("result");
		return nextPage;
	}
	
	//카테고리 추가
	@RequestMapping("/addCategory")
	public ModelAndView addCategory(HttpServletRequest request,@RequestParam(value="categoryName") String categoryName, 
															   @RequestParam(value="userId",required=false)String userId,
															   @RequestParam(value="parentId") int parentId) throws UnsupportedEncodingException{
		ModelAndView nextPage = new ModelAndView();
		if(userId == null)
			userId = (String)request.getSession().getAttribute("MEMBERID");
		
		String imgUrl = "images/folder.png";
		String status = "category";
		
		ArrayList<Position> currentPosition = new ArrayList<Position>();
		if(parentId != 0){ // 바탕화면이 아닐 경우 1,1 좌표는 비워둔다. 상위 카테고리로 올라가는 아이콘을 배치해야하기 때문임.
			currentPosition.add(new Position(1, 1));
		}

		Category category = new Category();
		category.setCategoryId(parentId);
		category.setUserId(userId);
		
		ArrayList<Position> d = new IndividualPageServiceImpl().bookMarkPos(category);
		
		currentPosition.addAll(d);

		// 아이콘이 추가될 x,y 좌표를 받아온다.
		Position newPosition = getPosition(currentPosition, 8, 4);

		category = new Category(categoryName, userId, newPosition.getPosX(), newPosition.getPosY(), imgUrl, status, parentId);
		int maxCategoryId = new IndividualPageServiceImpl().addCategory(category);
		category.setCategoryId(maxCategoryId);
		int maxBookmarkId = new IndividualPageServiceImpl().addMark(category);
		
		JSONObject jobj = new JSONObject();
		jobj.put("x", newPosition.getPosX());
		jobj.put("y", newPosition.getPosY());
		jobj.put("categoryId", maxCategoryId);
		jobj.put("bookmarkId", maxBookmarkId);
		jobj.put("imgUrl", imgUrl);
		jobj.put("categoryName", categoryName);
		
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
		return nextPage;
	}
}

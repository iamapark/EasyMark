package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.FriendshipServiceImpl;
import service.IndividualPageServiceImpl;
import util.AdminServer;
import util.MessageServer;
import dto.BookMark;
import dto.BookMarkShip;
import dto.Category;
import dto.FriendStatus;
import dto.Friendship;
import dto.Member;
import dto.Message;
import dto.Position;
import dto.User;


@Controller
public class FriendshipAction {

	public FriendshipAction(){
		MessageServer.getInstance().start();
	}

	private void traffic(){
		AdminServer.getInstance().trafficCount();
	}
	
	// 친구 목록 불러오기
	@RequestMapping("/getFriendList")
	public ModelAndView getFriendList(HttpServletRequest request, HttpServletResponse response) {
	
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		Member memberKey = new FriendshipServiceImpl().getKey(userId);
		
		String me2dayKey = memberKey.getMe2DayKey();
		
		if(me2dayKey == null)
			me2dayKey = "NotMe2Login";
		else
			me2dayKey = "Me2Login";	
		
		request.getSession().setAttribute("memberKey", me2dayKey);
		
		ArrayList<Member> friendList = null;
		Friendship friend = new Friendship(userId, "", "친구");
		friendList = new FriendshipServiceImpl().getFriendList(friend);
		
		ModelAndView mav = new ModelAndView();
		
		JSONArray dataJ = JSONArray.fromObject(friendList);
		System.out.println(dataJ);
		request.setAttribute("result", dataJ);
		
		mav.setViewName("result");
		
		traffic();
		return mav;
	}
	
	// 친구 요청 보낸 사용자 목록
	@RequestMapping("/sendFriendReqList")
	public ModelAndView sendFriendReqList(HttpServletRequest request, HttpServletResponse response) {

		String userId = (String)request.getSession().getAttribute("MEMBERID");
		String friendId = null;
		String status = "친구요청";

		ArrayList<Member> sendFriendReqList = null;

		Friendship friendship = new Friendship(userId, friendId, status);

		sendFriendReqList = new FriendshipServiceImpl().sendFriendReqList(friendship);
		
		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(sendFriendReqList);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}
	
	// 친구 요청 받은 사용자 목록
	@RequestMapping("/takeFriendReqList")
	public ModelAndView takeFriendReqList(HttpServletRequest request, HttpServletResponse response) {

		String userId = null;
		String friendId = (String)request.getSession().getAttribute("MEMBERID");
		String status = "친구요청";

		ArrayList<Member> takeFriendReqList = null;
			
		Friendship friendship = new Friendship(userId, friendId, status);

		takeFriendReqList = new FriendshipServiceImpl().takeFriendReqList(friendship);
	
		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(takeFriendReqList);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}
	
	// 추천 받은 북마크 목록 불러오기
	@RequestMapping("/recommendInWeb")
	public ModelAndView recommendInWeb(HttpServletRequest request, HttpServletResponse response) {

		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		ArrayList<BookMarkShip> recommendedWeb = new ArrayList<BookMarkShip>();
		recommendedWeb = new FriendshipServiceImpl().recommendInWeb(userId);
		
		ModelAndView mav = new ModelAndView();
	
		JSONArray dataJ = JSONArray.fromObject(recommendedWeb);
		request.setAttribute("result", dataJ);
		
		mav.setViewName("result");
		
		traffic();
		return mav;
	}

	// 추천 보낸 북마크 목록 불러오기
	@RequestMapping("/recommendOutWeb")
	public ModelAndView recommendOutWeb(HttpServletRequest request,
			HttpServletResponse response) {

		String userId = (String)request.getSession().getAttribute("MEMBERID");
		System.out.println("넘어온 ID : " + userId);

		ArrayList<BookMarkShip> recommendWeb = new ArrayList<BookMarkShip>();
		recommendWeb = new FriendshipServiceImpl().recommendOutWeb(userId);

		for (int i = 0; i < recommendWeb.size(); i++) {
			System.out.println(recommendWeb.get(i).getBookMarkName());
		}

		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(recommendWeb);
		request.setAttribute("result", dataJ);
		
		mav.setViewName("result");
		
		traffic();
		return mav;
	}

	// 북마크 추천하기
	@RequestMapping("/recommend")
	public ModelAndView recommend(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value="recommend_friendId")String friendId,
										  @RequestParam(value="recommend_url")String url,
										  @RequestParam(value="recommend_name")String name,
			  							  @RequestParam(value="recommend_descript")String descript) throws UnsupportedEncodingException {
		// 사용자ID 가져오기
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		String status = "추천";
		
		BookMarkShip bookmarkship = new BookMarkShip();
		boolean flag = false;
		ModelAndView mav = new ModelAndView();
	
		bookmarkship = new BookMarkShip(0, URLDecoder.decode(name, "utf-8"), url, URLDecoder.decode(descript, "utf-8"), userId, friendId, status);
		
		try {
			name = URLDecoder.decode(name, "utf-8");
			descript = URLDecoder.decode(descript, "utf-8");
		} catch (UnsupportedEncodingException e) {
	
		}
		flag = new FriendshipServiceImpl().bookMarkExist(bookmarkship);
		if(flag){
			new FriendshipServiceImpl().recommend(bookmarkship);
		}
		else{}
		
		request.setAttribute("result", flag);
		mav.setViewName("result");
		traffic();
		return mav;
	}
	
	// 친구 삭제
	@RequestMapping("/deleteFriend")
	public ModelAndView deleteFriend(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="friendId")String friendId) {
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		Friendship friendship = new Friendship(userId, friendId, "친구");
		new FriendshipServiceImpl().deleteFriend(friendship);
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("result", "true");
		mav.setViewName("result");
		
		return mav;
	}

	
	// 친구 요청 받은 것 수락
	@RequestMapping("/accept")
	public ModelAndView accept(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="userId")String userId) {
		
		String friendId = (String)request.getSession().getAttribute("MEMBERID");
		
		Friendship friendship = new Friendship(userId, friendId, "친구");
		
		new FriendshipServiceImpl().accept(friendship);
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("result", "true");
		mav.setViewName("result");
		
		return mav;
	}

	// 친구 요청받은 것 거절
	@RequestMapping("/reject")
	public ModelAndView reject(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(value="userId")String userId) {
		
		String friendId = (String)request.getSession().getAttribute("MEMBERID");
	
		Friendship friendship = new Friendship(userId, friendId, "친구요청");
		new FriendshipServiceImpl().reject(friendship);
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("result", "true");
		mav.setViewName("result");
		
		return mav;
	}
	
	// 내가 친구 요청 보낸 것 취소하기
	@RequestMapping("/cancel")
	public ModelAndView cancel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="friendId")String friendId) {
		String userId = (String)request.getSession().getAttribute("MEMBERID");
				
		Friendship friendship = new Friendship(userId, friendId, "친구요청");

		new FriendshipServiceImpl().cancel(friendship);
		
		ModelAndView mav = new ModelAndView();
		request.setAttribute("result", "true");
		mav.setViewName("result");
		
		return mav;
	}
		
	// 친구 요청 보내기(친구 추가 버튼)
	@RequestMapping("/requestFriend")
	public ModelAndView requestFriend(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="friendId")String friendId) {
			
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		Friendship friendship = new Friendship(userId, friendId, "친구요청");

		new FriendshipServiceImpl().requestFriend(friendship);

		ModelAndView mav = new ModelAndView();
		request.setAttribute("result", "true");
		mav.setViewName("result");
			
		return mav;
	}
		
	// 받은 편지 목록 불러오기
	@RequestMapping("/inBox")
	public ModelAndView inBox(HttpServletRequest request,
			HttpServletResponse response) {

		String userId = (String)request.getSession().getAttribute("MEMBERID");

		ArrayList<Message> inMessage = null;
		Message message = new Message(0, userId, "", null, "", new Date(), "", 0, "take");

		inMessage = new FriendshipServiceImpl().inBox(message);
		for (int i = 0; i < inMessage.size(); i++) {
			String dateTime = StringFromCalendar(inMessage.get(i).getMessageDate());
			inMessage.get(i).setMessageDate2(dateTime);
		}

		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(inMessage);

		request.setAttribute("result", dataJ);
		mav.setViewName("result");
			
		traffic();
		return mav;
	}

	// 보낸 편지 목록 불러오기
	@RequestMapping("/outBox")
	public ModelAndView outBox(HttpServletRequest request,
			HttpServletResponse response) {

		String userId = (String)request.getSession().getAttribute("MEMBERID");

		ArrayList<Message> outMessage = null;
		Message message = new Message(0, userId, "", null, "", new Date(), "", 0, "send");
		outMessage = new FriendshipServiceImpl().outBox(message);
		
		for (int i = 0; i < outMessage.size(); i++) {
			String dateTime = StringFromCalendar(outMessage.get(i).getMessageDate());
			outMessage.get(i).setMessageDate2(dateTime);
		}

		ModelAndView mav = new ModelAndView();
		
		JSONArray dataJ = JSONArray.fromObject(outMessage);

		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}	
		
	// 날짜 변경함수
	public static String StringFromCalendar(Date date) {
	
		// 날짜를 통신용 문자열로 변경
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date.getTime());
	}
	
	// 친구 리스트
	@RequestMapping("/getMessageFriendInfo")
	public ModelAndView getMessageFriendInfo(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView nextPage = new ModelAndView();
		
		String userId = (String) request.getSession().getAttribute("MEMBERID");
		
		Friendship friend = new Friendship(userId, "", "친구");
		ArrayList<Member> friendList = new FriendshipServiceImpl().getFriendList(friend);
		
		JSONArray friendListJ = JSONArray.fromObject(friendList);
		
		String data = friendListJ.toString();
		
		request.setAttribute("result", friendListJ.toString());
		nextPage.setViewName("result");

		traffic();
		return nextPage;
	}
	
	// 메시지 보내기
	@RequestMapping("/sendMessage")
	public ModelAndView sendMessage(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value="messageFriendId")String friendId,
			  							  @RequestParam(value="messageContents")String contents) throws UnsupportedEncodingException {
			
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		int messageNum = 0;

		ModelAndView mav = new ModelAndView();
		ArrayList<Message> newMessage = new ArrayList<Message>();
		
		Message msg = new Message(messageNum, userId, friendId, null, URLDecoder.decode(contents, "utf-8"), new Date(), "", 0, "send");
		boolean flag = new FriendshipServiceImpl().sendMessage(msg);
			
		try {
			contents = URLDecoder.decode(contents, "utf-8");
		} catch (UnsupportedEncodingException e) {
	
		}
		
		msg = new Message(messageNum, userId, friendId, null, URLDecoder.decode(contents, "utf-8"), new Date(), "", 0, "take");

		new FriendshipServiceImpl().sendMessage(msg);
		
		if (flag) { // 메시지 DB 등록 성공
			
			Message message = new Message(0, userId, "", null, "", new Date(), "", 0, "take");
			newMessage = new FriendshipServiceImpl().messageCount(message);
			
			MessageServer.getInstance().sendMessage(friendId, userId, contents, newMessage.size());
			
			traffic();
		}
		
		System.out.println("newMessage.size(): " + newMessage.size());
		
		JSONObject jobj = new JSONObject();
		jobj.put("friendId", friendId);
		jobj.put("contents",  URLDecoder.decode(contents, "utf-8"));
		jobj.put("num", newMessage.size());
		request.setAttribute("result", jobj);
		mav.setViewName("result");
		return mav;
		
	}
		
	// 현재 로그인 중인 아이디를 반환
	@RequestMapping("/isContains")
	public ModelAndView isContains(HttpServletRequest request, HttpServletResponse response){
	
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		ModelAndView mav = new ModelAndView();
		JSONObject jobj = new JSONObject();
		jobj.put("user", userId);
		
		request.setAttribute("result", jobj);
		mav.setViewName("result");
		
		return mav;
	}

	// 북마크 추천 받은 것 거절, 보낸 것 취소
	@RequestMapping("/recommendCancel")
	public ModelAndView recommendCancel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="bookMarkId")String bookMarkId) {
		
		new FriendshipServiceImpl().recommendCancel(bookMarkId);
		ModelAndView mav = new ModelAndView();
		request.setAttribute("result", "true");
		mav.setViewName("result");
			
		return mav;
	}
		
	// 북마크 추천 받은 것 수락	
	@RequestMapping("/recommendAccept")
	public ModelAndView recommendAccept(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="bookMarkId")String bookMarkId,
			@RequestParam(value="bookMarkUrl")String bookMarkUrl,
			@RequestParam(value="bookMarkName")String bookMarkName,
			@RequestParam(value="bookMarkDescript")String bookMarkDescript) throws UnsupportedEncodingException {
		
		String friendId = (String)request.getSession().getAttribute("MEMBERID");
		String status = "bookmark";
		String userId = (String) request.getSession().getAttribute("MEMBERID");
		
		Category category = new Category();
		category.setCategoryId(0);
		category.setUserId(userId);
		ArrayList<Position> currentPosition = new IndividualPageServiceImpl().bookMarkPos(category);
		
		// 아이콘이 추가될 x,y 좌표를 받아온다.
		Position newPosition = IndividualPageAction.getPosition(currentPosition, 8, 4);
		
		String imgUrl = null;
		imgUrl = "images/Bookmark.png";

		BookMark bookMark = new BookMark(0, URLDecoder.decode(bookMarkName, "utf-8"), bookMarkUrl, URLDecoder.decode(bookMarkDescript, "utf-8"), friendId,
				status, newPosition.getPosX(), newPosition.getPosY(), imgUrl, 0, "0");

		try {
			bookMarkName = URLDecoder.decode(bookMarkName, "utf-8");
			bookMarkDescript = URLDecoder.decode(bookMarkDescript, "utf-8");
		} catch (UnsupportedEncodingException e) {
	
		}
		
		new FriendshipServiceImpl().recommendCancel(bookMarkId);
		int maxBookmarkId = new IndividualPageServiceImpl().addBookMark(bookMark);
		
		ModelAndView mav = new ModelAndView();
		JSONObject jobj = new JSONObject();
		jobj.put("x", newPosition.getPosX());
		jobj.put("y", newPosition.getPosY());
		jobj.put("id", maxBookmarkId);
		jobj.put("imgUrl", imgUrl);
		jobj.put("url", bookMarkUrl);

		request.setAttribute("result", jobj);
		mav.setViewName("result");
		
		return mav;
	}
	
	// 아이디 검색했을 때 결과 목록
	@RequestMapping("/memberList")
	public ModelAndView memberList(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="keyword")String keyword) {
		
		String loginId = (String)request.getSession().getAttribute("MEMBERID");
		System.out.println(loginId);
		User userSearch = new User(loginId, keyword); // 사용자Id, keyword

		ArrayList<Member> searchList = new ArrayList<Member>();
		searchList = new FriendshipServiceImpl().searchFriend(userSearch);
		// keyword 를 포함하는 ID를 ArrayList로 불러온다.
		ArrayList<FriendStatus> friendStatusList = new ArrayList<FriendStatus>();

		ArrayList<FriendStatus> friend = new ArrayList<FriendStatus>();
			// 사용자Id, key가 포함된 아이디 -> keyword 포함된 아이디와 userId 제외한 Id 검색하여 사용자 정보 출력

		for (int i = 0; i < searchList.size(); i++) { // keyword 포함하는 ID를 검사한다.
			FriendStatus friendStatus = new FriendStatus(loginId, searchList.get(i).getUserId(), searchList.get(i).getName(), searchList.get(i).getEmail(), "");
				// keyword 포함 ID 1개씩 friendStatus로 넣는다.

			friend = new FriendshipServiceImpl().getFriendStatus(friendStatus);

			if (friend.size() == 0) {
				
				String status = "친구아님";
				friendStatus = new FriendStatus(loginId, searchList.get(i).getUserId(), searchList.get(i).getName(), searchList.get(i).getEmail(), status);
				friendStatusList.add(friendStatus);
;
			}

			else {
				for (int k = 0; k < friend.size(); k++) {
					if (friend.get(k).getUserId().equals(loginId) || friend.get(k).getFriendId().equals(loginId)) {
						friendStatusList.add(friend.get(k));
					}
				}
			}
		}
		ModelAndView mav = new ModelAndView();
		
		JSONArray dataJ = JSONArray.fromObject(friendStatusList);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}	
		
		
	// 메시지 삭제
	@RequestMapping("/deleteMessage")
	public ModelAndView deleteMessage(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "messageId") String messageId) {
			
		String ar[] = messageId.split("&");
		ArrayList<String> idList = new ArrayList<String>();
		for(String a:ar){
			idList.add(a.split("=")[1]);
		}
			
		for (int i = 0; i < idList.size(); i++) {
			new FriendshipServiceImpl().deleteMessage(idList.get(i));
		}
			
		ModelAndView mav = new ModelAndView();
		request.setAttribute("result", "true");
		mav.setViewName("result");
		
		return mav;
	}	
	
	// 읽지 않은 메시지 카운트
	@RequestMapping("/messageCount")
	public ModelAndView messageCount(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		String loginId = (String)request.getSession().getAttribute("MEMBERID");
		Message message = new Message(0, loginId, "", null, "", new Date(), "", 0, "take");
		ArrayList<Message> newMessage = new FriendshipServiceImpl().messageCount(message);
		int newMessageCount = newMessage.size();
		
		request.setAttribute("result", newMessageCount);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}
	
	// 메시지 상세 보기
	@RequestMapping("/messageView")
	public ModelAndView messageView(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "messageId") String messageId) {

		Message message = new Message();
	
		message = new FriendshipServiceImpl().getMessage(messageId);
		
		String dateTime = StringFromCalendar(message.getMessageDate());
		String loginId = (String)request.getSession().getAttribute("MEMBERID");
		if(message.getFriendId().equals(loginId)){
			message = new Message(message.getMessageId(), message.getUserId(),
					  message.getFriendId(), message.getTitle(),
					  message.getContents(), message.getMessageDate(), dateTime, message.getReadNum()+1, message.getType());
		} else {
			
			message = new Message(message.getMessageId(), message.getUserId(),
					  message.getFriendId(), message.getTitle(),
					  message.getContents(), message.getMessageDate(), dateTime, message.getReadNum(), message.getType());
		}
		
		new FriendshipServiceImpl().updateMessage(message);

		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(message);
		JSONObject data = new JSONObject();
		data.put("num", 2);
		dataJ.add(data);

		request.setAttribute("result", dataJ.toString());
		mav.setViewName("result");
		
		traffic();
		return mav;
	}	
	
	// 미투데이친구 + EasyMark 회원 목록 불러오기
	@RequestMapping("/me2dayFriend")
	public ModelAndView me2dayFriend(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "friendsId") String friendsId) {
			
		String loginId = (String)request.getSession().getAttribute("MEMBERID");
		
		String[] me2Friends = friendsId.split(",");
		ArrayList<Member> me2FriendList = new ArrayList<Member>();
		ArrayList<FriendStatus> friendStatusList = new ArrayList<FriendStatus>();
		Member member = new Member();

		for (int i = 0; i < me2Friends.length; i++) {
			
			me2Friends[i] = me2Friends[i] + "@me2day";
			String userId = me2Friends[i];

			member = new FriendshipServiceImpl().me2dayFriend(userId);

			if (member != null) {
				me2FriendList.add(member);
			}
		}
			
		ArrayList<FriendStatus> friend = new ArrayList<FriendStatus>();
			
		for (int i = 0; i < me2FriendList.size(); i++) {

			FriendStatus friendStatus = new FriendStatus(loginId, me2FriendList
					.get(i).getUserId(), me2FriendList.get(i).getName(), me2FriendList.get(i).getEmail(), "");
				
			friend = new FriendshipServiceImpl().getFriendStatus(friendStatus);

			if (friend.size() == 0) {
				String status = "친구아님";
				friendStatus = new FriendStatus(loginId, me2FriendList.get(i)
						.getUserId(), me2FriendList.get(i).getName(), me2FriendList.get(i).getEmail(), status);
				friendStatusList.add(friendStatus);

			}
			
			for (int k = 0; k < friend.size(); k++) {
				
				if (friend.get(k).getUserId().equals(loginId) || friend.get(k).getFriendId().equals(loginId)) {
					friendStatusList.add(friend.get(k));
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(friendStatusList);

		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}		
}

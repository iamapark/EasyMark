package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import util.RelayServer;
import dto.BookMark;
import dto.BookMarkShip;
import dto.FriendStatus;
import dto.Friendship;
import dto.Member;
import dto.Message;
import dto.User;


@Controller
public class FriendshipAction {
	private RelayServer relayServer;
	//private MessageServer msgServer;
	private void traffic(){
		AdminServer.getInstance().trafficCount();
	}
	
	// 친구 목록 불러오기
	@RequestMapping("/friend")
	public ModelAndView friend(HttpServletRequest request, HttpServletResponse response) {
	
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		System.out.println(userId);
		
		Member memberKey = new FriendshipServiceImpl().getKey(userId);
		
		System.out.println(memberKey.getMe2DayKey());
		
		String me2dayKey = memberKey.getMe2DayKey();
		
		if(me2dayKey == null)
			me2dayKey = "NotMe2Login";
		else
			me2dayKey = "Me2Login";	
		
		System.out.println(me2dayKey);
		
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
	@RequestMapping("/sendFriendReq")
	public ModelAndView sendFriendReq(HttpServletRequest request, HttpServletResponse response) {

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
	@RequestMapping("/takeFriendReq")
	public ModelAndView takeFriendReq(HttpServletRequest request, HttpServletResponse response) {

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
		
		System.out.println("추천받은사이트");
		ArrayList<BookMarkShip> recommendedWeb = new ArrayList<BookMarkShip>();
		recommendedWeb = new FriendshipServiceImpl().inWeb(userId);
		
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
		recommendWeb = new FriendshipServiceImpl().outWeb(userId);

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
			  							  @RequestParam(value="recommend_descript")String descript) {
		// 사용자ID 가져오기
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		String status = "추천";
		
		BookMarkShip bookmarkship = new BookMarkShip();
		String[] selectFriend = friendId.split(",");
		boolean flag = false;
		ModelAndView mav = new ModelAndView();
		
		for (int i = 0; i < selectFriend.length; i++) {
			selectFriend[i] = selectFriend[i].trim();
			bookmarkship = new BookMarkShip(0, name, url, descript, userId, selectFriend[i], status);
			
			flag = new FriendshipServiceImpl().bookMarkExist(bookmarkship);
			System.out.println("flag:"+flag);
			if(flag){
				new FriendshipServiceImpl().recommendSite(bookmarkship);
				
			}
			else{
			}
			System.out.println(bookmarkship.toString());
			
			request.setAttribute("result", flag);
			mav.setViewName("result");
			traffic();
			
		}return mav;
	}
	
	
	// 친구 삭제
	@RequestMapping("/deleteFriend")
	public ModelAndView deleteFriend(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="friendId")String friendId) {
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		System.out.println(friendId);
		
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
		System.out.println(friendship.getUserId());
		System.out.println(friendship.getFriendId());
		System.out.println(friendship.getStatus());
		
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
		System.out.println(friendship.getUserId());
		System.out.println(friendship.getFriendId());
		System.out.println(friendship.getStatus());
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
		
		System.out.println(userId + ";" + friendId);
			
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
		System.out.println(userId);

		ArrayList<Message> inMessage = null;

		inMessage = new FriendshipServiceImpl().getInBox(userId);
		for (int i = 0; i < inMessage.size(); i++) {
			System.out.println(inMessage.get(i).getMessageDate());
			String dateTime = StringFromCalendar(inMessage.get(i).getMessageDate());
			inMessage.get(i).setMessageDate2(dateTime);
		}

		ModelAndView mav = new ModelAndView();
			
		JSONArray dataJ = JSONArray.fromObject(inMessage);
		System.out.println(dataJ);
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
		System.out.println(userId);
		ArrayList<Message> outMessage = null;
		outMessage = new FriendshipServiceImpl().getOutBox(userId);
		for (int i = 0; i < outMessage.size(); i++) {
			System.out.println(outMessage.get(i).getMessageDate());
			String dateTime = StringFromCalendar(outMessage.get(i).getMessageDate());
			outMessage.get(i).setMessageDate2(dateTime);
		}

		ModelAndView mav = new ModelAndView();
		
		JSONArray dataJ = JSONArray.fromObject(outMessage);
		System.out.println(dataJ);
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
	
	// 메시지 보내기
	@RequestMapping("/sendMessage")
	public void sendMessage(HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value="messageFriendId")String friendId,
			  							  @RequestParam(value="messageContents")String contents) {
		System.out.println("sendMessage() 호출!!");
			
		String userId = (String)request.getSession().getAttribute("MEMBERID");
			
		int messageNum = 0;

		Message msg = new Message(messageNum, userId, friendId, null, contents, new Date(), "");

		System.out.println(new Date());

		boolean flag = new FriendshipServiceImpl().sendMessage(msg);
		// boolean flag = true;
		if (flag) { // 메시지 DB 등록 성공
			
			MessageServer.getInstance().sendMessage(friendId, contents);
			// msgServer.sendMessage(friendId, message);

			//relayServer.sendMessage(friendId, contents);  X

		} else { // 메시지 DB 등록 실패
				System.out.println("쪽지 보내기 실패요 ㅋㅋ");
		}
	}
		
	@RequestMapping("/isContains")
	public ModelAndView isContains(HttpServletRequest request, HttpServletResponse response){
	
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		//boolean flag = relayServer.isContains(userId);
		/*request.setAttribute("result", Boolean.toString(flag));
		nextPage.setViewName("/views/result.jsp");
			
		return nextPage;*/
		ModelAndView mav = new ModelAndView();
		
		request.setAttribute("result", userId);
		mav.setViewName("result");
			
		traffic();
		return mav;
	}

	// 북마크 추천 받은 것 거절, 보낸 것 취소
	@RequestMapping("/recommendCancel")
	public ModelAndView recommendCancel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="bookMarkId")String bookMarkId) {
		
		new FriendshipServiceImpl().bookmarkCancel(bookMarkId);
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
		String status = "false";
		int posx = 0;
		int posy = 0;
		ArrayList<BookMark> bookMarkList = null;
		// 현재 사용자의 북마크 리스트 가져오기
		bookMarkList = new IndividualPageServiceImpl().bookMarkList(friendId);

		// 만약 처음 북마크 추가이면 1,1 위치 삽입
		if (bookMarkList.size() == 0) {
			posx = 1;
			posy = 1;
		} else {// //////////추가한 아이콘 제일 마지막 아이콘 옆에 배치!!
			posx = new IndividualPageServiceImpl().bookMarkPosx(friendId);
			System.out.println("x=" + posx);
			// ParameterClass 2개라서 HashMap 이용 맞나?
			HashMap<String, Object> pos = new HashMap<String, Object>();
			pos.put("userId", friendId);
			pos.put("posX", posx);
			posy = new IndividualPageServiceImpl().bookMarkPosy(pos); // x 줄에 제일
																		// 마지막
																		// y 값
			System.out.println("by=" + posy);
			posy++; // +1해서 다음에 놓을 곳 배치
			System.out.println("ay=" + posy);
			if (posy == 7) {// 다음줄로 넘기기
				posx++;
				posy = 1;
			}

		}
		String imgUrl = null;
		imgUrl = "images/Bookmark.png";
		//BookMark bookMark = new BookMark(0, bookMarkName, bookMarkUrl, bookMarkDescript, friendId, status, posx, posy);
		BookMark bookMark = new BookMark(0, URLDecoder.decode(bookMarkName, "utf-8"), bookMarkUrl, bookMarkDescript, friendId,
				status, posx, posy, imgUrl, 0, "");
		//new IndividualPageServiceImpl().addBookMark(bookMark);
		new FriendshipServiceImpl().bookmarkCancel(bookMarkId);
		/*ModelAndView mav = new ModelAndView();
		
		request.setAttribute("result", "true");
		mav.setViewName("result");
		*/
		int maxBookmarkId = new IndividualPageServiceImpl().addBookMark(bookMark);
		
		ModelAndView mav = new ModelAndView();
		JSONObject jobj = new JSONObject();
		jobj.put("x", posx);
		jobj.put("y", posy);
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

			System.out.println("**" + searchList.get(i).getUserId());

			friend = new FriendshipServiceImpl().getFriendStatus(friendStatus);

			if (friend.size() == 0) {
				
				String status = "친구아님";
				friendStatus = new FriendStatus(loginId, searchList.get(i).getUserId(), searchList.get(i).getName(), searchList.get(i).getEmail(), status);
				friendStatusList.add(friendStatus);
				System.out.println(friendStatus.getUserId() + ":"
							+ friendStatus.getFriendId() + ":"
							+ friendStatus.getStatus());
			}

			else {
				for (int k = 0; k < friend.size(); k++) {
					System.out.println(friend.get(k).getStatus());
					if (friend.get(k).getUserId().equals(loginId) || friend.get(k).getFriendId().equals(loginId)) {
						friendStatusList.add(friend.get(k));
					}
				}
			}
		}
		ModelAndView mav = new ModelAndView();
		
		JSONArray dataJ = JSONArray.fromObject(friendStatusList);
		//System.out.println(dataJ);
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
		System.out.println(messageId);
			
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
		
		
	// 메시지 상세 보기
	@RequestMapping("/messageView")
	public ModelAndView messageView(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "messageId") String messageId) {
		System.out.println(messageId);
		Message message = new Message();
	
		message = new FriendshipServiceImpl().getMessage(messageId);

		String dateTime = StringFromCalendar(message.getMessageDate());
		message = new Message(message.getMessageId(), message.getUserId(),
							  message.getFriendId(), message.getTitle(),
							  message.getContents(), message.getMessageDate(), dateTime);

		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(message);
		System.out.println(dataJ);
		request.setAttribute("result", dataJ);
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
		
		System.out.println("===" + loginId + "===");

		String[] me2Friends = friendsId.split(",");
		ArrayList<Member> me2FriendList = new ArrayList<Member>();
		ArrayList<FriendStatus> friendStatusList = new ArrayList<FriendStatus>();
		Member member = new Member();
		System.out.println(me2Friends.length);
		for (int i = 0; i < me2Friends.length; i++) {
			
			me2Friends[i] = me2Friends[i] + "@me2day";
			String userId = me2Friends[i];
			System.out.println("userid : "+userId);
			member = new FriendshipServiceImpl().me2Friend(userId);

			if (member != null) {
				System.out.println(member.getUserId());
				me2FriendList.add(member);
			} else
				System.out.println("가입 된 친구 아님!!");
			}
			
		ArrayList<FriendStatus> friend = new ArrayList<FriendStatus>();
			
		for (int i = 0; i < me2FriendList.size(); i++) {

			FriendStatus friendStatus = new FriendStatus(loginId, me2FriendList
					.get(i).getUserId(), me2FriendList.get(i).getName(), me2FriendList.get(i).getEmail(), "");
				
			System.out.println(i+','+friendStatus.getFriendId()+','+friendStatus.getName());
				
			friend = new FriendshipServiceImpl().getFriendStatus(friendStatus);

			if (friend.size() == 0) {
				String status = "친구아님";
				friendStatus = new FriendStatus(loginId, me2FriendList.get(i)
						.getUserId(), me2FriendList.get(i).getName(), me2FriendList.get(i).getEmail(), status);
				friendStatusList.add(friendStatus);

				System.out.println(status);
			}
			
			for (int k = 0; k < friend.size(); k++) {
				System.out.println("id:"+friend.get(k).getUserId()+":"+friend.get(k).getStatus());
				System.out.println(friend.get(k).getUserId()+','+friend.get(k).getFriendId());
				
				if (friend.get(k).getUserId().equals(loginId) || friend.get(k).getFriendId().equals(loginId)) {
					friendStatusList.add(friend.get(k));
				}
			}
		}
		
		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(friendStatusList);
		System.out.println(dataJ);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		traffic();
		return mav;
	}		
}

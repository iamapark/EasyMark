package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.FriendshipServiceImpl;
import dto.BookMarkShip;
import dto.Friendship;
import dto.Member;


@Controller
public class FriendshipAction {
	
	@RequestMapping("/friend")
	public ModelAndView friend(HttpServletRequest request, HttpServletResponse response) {
	
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		
		System.out.println(userId);
		
		/*Member memberKey = null;
		memberKey = new FriendshipServiceImpl().getKey(userId);
		System.out.println(memberKey.getMe2DayKey());
		
		String me2dayKey = null;
		
		if(memberKey.getMe2DayKey().equals("NULL")){
			me2dayKey = "NotMe2Login";
		}
		else
			me2dayKey = "me2dayLogin";
		*/
				
		ArrayList<Member> friendList = null;
		Friendship friend = new Friendship(userId, "", "친구");
		friendList = new FriendshipServiceImpl().getFriendList(friend);
		
		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(friendList);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		return mav;
		
		/*ModelAndView nextPage = new ModelAndView();
		nextPage.setViewName("view/modal/friend");
		request.setAttribute("friendList", friendList);
		request.getSession().setAttribute("memberKey", me2dayKey);
		return nextPage;*/
	}
	
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
		return mav;
		
		/*ModelAndView nextPage = new ModelAndView();
		nextPage.setViewName("views/friendship/sendFriendReq");
		request.setAttribute("sendFriendReqList", sendFriendReqList);
		return nextPage;*/
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
		
		return mav;
			
			/*ModelAndView nextPage = new ModelAndView();
			nextPage.setViewName("views/friendship/takeFriendReq.jsp");
			request.setAttribute("takeFriendReqList", takeFriendReqList);
			return nextPage;*/
	}
	
	@RequestMapping("/recommendInWeb")
	public ModelAndView recommendInWeb(HttpServletRequest request,
			HttpServletResponse response) {

		String userId = request.getParameter("userId");

		ArrayList<BookMarkShip> recommendedWeb = new ArrayList<BookMarkShip>();
		recommendedWeb = new FriendshipServiceImpl().inWeb(userId);

		ModelAndView mav = new ModelAndView();
		JSONArray dataJ = JSONArray.fromObject(recommendedWeb);
		request.setAttribute("result", dataJ);
		mav.setViewName("result");
		
		return mav;
		
		/*request.setAttribute("inWebList", recommendedWeb);
		ModelAndView nextPage = new ModelAndView();
		nextPage.setViewName("views/friendship/recommendWeb.jsp");

		return nextPage;*/
	}

	@RequestMapping("/recommendOutWeb")
	public ModelAndView recommendOutWeb(HttpServletRequest request,
			HttpServletResponse response) {

		String userId = request.getParameter("userId");
		System.out.println("넘어온 ID : " + userId);

		ArrayList<BookMarkShip> recommendWeb = new ArrayList<BookMarkShip>();
		recommendWeb = new FriendshipServiceImpl().outWeb(userId);

		for (int i = 0; i < recommendWeb.size(); i++) {
			System.out.println(recommendWeb.get(i).getBookMarkName());
		}

		request.setAttribute("outWebList", recommendWeb);

		JSONArray inList = JSONArray.fromObject(recommendWeb);
		request.setAttribute("result", inList.toString());
		System.out.println(inList.toString());

		ModelAndView nextPage = new ModelAndView();
		nextPage.setViewName("/views/result.jsp");

		
		return nextPage;
	}
}

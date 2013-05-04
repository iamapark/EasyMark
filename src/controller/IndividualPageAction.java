package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.IndividualPageServiceImpl;
import dto.BookMark;


@Controller
public class IndividualPageAction {
	
	@RequestMapping("/addMark")
	public ModelAndView addBookMark(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="name") String name,
																							  @RequestParam(value="url") String url,
																							  @RequestParam("description")String description){
		ModelAndView nextPage = new ModelAndView();
		
		//사용자ID 가져오기
		HttpSession session = request.getSession();
		String userId=(String)session.getAttribute("MEMBERID");
		String status="false";
		int posx=0;
		int posy=0;
		
		ArrayList<BookMark> bookMarkList = null;
		//현재 사용자의 북마크 리스트 가져오기
		bookMarkList=new IndividualPageServiceImpl().bookMarkList(userId);
		
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
		
		//위치가 여기 저기 떨어져 있으면 북마크 추가할때 어디다 배치해야되나??
		BookMark bookMark=new BookMark(0,name, url, description, userId, status, posx, posy);
		new IndividualPageServiceImpl().addBookMark(bookMark);
		bookMarkList=new IndividualPageServiceImpl().bookMarkList(userId);
		
		session.setAttribute("bookMarkList", bookMarkList);
		nextPage.setViewName("main");
		
		return nextPage;
	}
	
	@RequestMapping("/getBookMarkList")
	public ModelAndView getBookMarkList(HttpServletRequest request, HttpServletResponse response){
		ModelAndView nextPage = new ModelAndView();
		
		//현재 사용자의 북마크 리스트 가져오기
		ArrayList<BookMark> bookMarkList 
					= new IndividualPageServiceImpl().bookMarkList((String)request.getSession().getAttribute("MEMBERID"));
		
		JSONArray dataJ = JSONArray.fromObject(bookMarkList);
		request.setAttribute("result", dataJ);
		nextPage.setViewName("result");
		return nextPage;
	}

	
}

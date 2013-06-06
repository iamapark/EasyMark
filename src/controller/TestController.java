package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.vertx.java.core.json.JsonArray;
import org.vertx.java.core.json.JsonObject;

import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import dto.BookMark;
import dto.Category;
import dto.Img;
import dto.Login;
import dto.Member;


@Controller
public class TestController {
	
	@RequestMapping("/go.do")
	public ModelAndView go(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="page") String page){
		System.out.println(page);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(page); // /WEB-INF/view/main.jsp 페이지로 이동
		return mav;
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
		
		ModelAndView mav = new ModelAndView();
		Login login = new Login(userId, password);
		boolean flag = false;
		
		flag = new MembershipServiceImpl().login(login);
		
		System.out.println(flag);
		
		request.setAttribute("result", Boolean.toString(flag)); 
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping("/getCategoryTree")
	public ModelAndView connector(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("MEMBERID");
		
		ArrayList<Category> categoryList = new IndividualPageServiceImpl().getCategoryList(userId);
		
		request.setAttribute("result", getCategoryTree(categoryList).toString());
		mav.setViewName("result");
		return mav;
	}
	
	private JsonObject getJsonObject(ArrayList<Category> categoryList, int categoryId){
		
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
	
	private JsonArray getJsonArray(ArrayList<Category> categoryList, int parentId){
		
		JsonArray result = new JsonArray();
		ArrayList<Category> count = getParentCategoryCount(categoryList, parentId);
		
		for(int i=0; i<count.size(); i++){
			result.addObject(getJsonObject(categoryList, count.get(i).getCategoryId()));
		}
		
		return result;
	}
	
	private ArrayList<Category> getParentCategoryCount(ArrayList<Category> categoryList, int parentId){
		ArrayList<Category> result = new ArrayList<Category>();
		
		for(Category c: categoryList){
			if(parentId == c.getParentCategoryId())
				result.add(c);
		}
		
		return result;
	}
	
	private Category getCategory(ArrayList<Category> categoryList, int index){
		Category result = null;
		
		for(Category c: categoryList){
			if(index == c.getCategoryId())
				result = c;
		}
		
		return result;
	}
	
	private boolean isParentIdExist(ArrayList<Category> categoryList, int parentId){
		boolean result = false;
		
		for(Category c: categoryList){
			if(c.getParentCategoryId() == parentId){
				result = true;
			}
		}
		
		return result;
	}
	
	private JsonObject getCategoryTree(ArrayList<Category> categoryList){
		JsonObject jData = getJsonObject(categoryList, 0);
		
		return jData;
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
		
		int posx=0;
		int posy=0;
		ArrayList<BookMark> bookMarkList = null;
		//사용자의 현재 해당하는 카테고리 북마크 리스트 가져오기
		HashMap<String, Object> bookMarkInfo = new HashMap<>();
		bookMarkInfo.put("userId", userId);
		bookMarkInfo.put("parentId", parentId);
		bookMarkList = new IndividualPageServiceImpl().bookMarkList(bookMarkInfo);
		
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
			if(posy==9){//다음줄로 넘기기
				posx++;
				posy=1;
			}
			
		}
		

		Category category = new Category(categoryName, userId, posx, posy, imgUrl, status, parentId);
		int maxCategoryId = new IndividualPageServiceImpl().addCategory(category);
		category.setCategoryId(maxCategoryId);
		int maxBookmarkId = new IndividualPageServiceImpl().addMark(category);
		
		JSONObject jobj = new JSONObject();
		jobj.put("x", posx);
		jobj.put("y", posy);
		jobj.put("categoryId", maxCategoryId);
		jobj.put("bookmarkId", maxBookmarkId);
		jobj.put("imgUrl", imgUrl);
		jobj.put("categoryName", categoryName);
		
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
		return nextPage;
	}
	
	
}

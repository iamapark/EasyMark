package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.json.simple.JSONValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.IndividualPageServiceImpl;
import service.MembershipServiceImpl;
import util.FileWriter;
import dto.BookMark;
import dto.Img;
import dto.Member;


@Controller
public class IndividualPageAction {
	
	@RequestMapping("/addMark")
	public ModelAndView addBookMark(HttpServletRequest request, Img img, @RequestParam(value="addBookMarkImage",required=false)MultipartFile file,
																		 @RequestParam(value="name") String name,
																		 @RequestParam(value="url") String url,
																		 @RequestParam(value="description")String description,
																		 @RequestParam(value="category")String category){
		ModelAndView nextPage = new ModelAndView();
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		String imgUrl = null;
		
		//이미지가 저장되는 경로
		String path = request.getSession().getServletContext().getRealPath("/users/img/")+"/" + userId + "/bookmark/";
		System.out.println(path);
		
		if(file != null){
			if(!file.getOriginalFilename().equals("")){
				new FileWriter().writeFile(file, path, file.getOriginalFilename());
				imgUrl = "users/img/" + userId + "/bookmark/" + file.getOriginalFilename();
			}else{
				imgUrl = "images/Bookmark.png";
			}
		}else{
			imgUrl = "images/Bookmark.png";
		}
		
		//사용자ID 가져오기
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
		
		BookMark bookMark = new BookMark(0,name, url, description, userId, status, posx, posy, imgUrl, 0);
		int maxBookmarkId = new IndividualPageServiceImpl().addBookMark(bookMark);
		System.out.println("maxBookmarkId: " + maxBookmarkId);
		bookMarkList = new IndividualPageServiceImpl().bookMarkList(userId);
		request.getSession().setAttribute("bookMarkList", bookMarkList);
		
		Member m = new MembershipServiceImpl().getMemberInfo(userId);
		request.getSession().setAttribute("MEMBERINFO", m);
		
		JSONObject jobj = new JSONObject();
		jobj.put("x", posx);
		jobj.put("y", posy);
		jobj.put("id", maxBookmarkId);
		jobj.put("imgUrl", imgUrl);
		
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		
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

	@RequestMapping("/getBookmarkInfo")
	public ModelAndView getBookmarkInfo(HttpServletRequest request,
			HttpServletResponse response){
		ModelAndView nextPage = new ModelAndView();
		BookMark bookMark=new BookMark();
		int bookMarkId = Integer.parseInt(request.getParameter("bookMarkId"));
		
		bookMark=new IndividualPageServiceImpl().getBookMark(bookMarkId);
	
		JSONArray bookMarkJ = JSONArray.fromObject(bookMark);
		request.setAttribute("result", bookMarkJ.toString());
		nextPage.setViewName("result");
		
		return nextPage;
	}

	@RequestMapping("/modifyMark")
	public ModelAndView modifymark(HttpServletRequest request, Img img,
										   		   @RequestParam(value="modifyBookmarkImage",required=false)MultipartFile file,
												   @RequestParam(value="modifyBookmarkName")String name,
												   @RequestParam(value="modifyBookmarkUrl")String url,
												   @RequestParam(value="modifyBookmarkDescription")String desc,
												   @RequestParam(value="bookmarkId")int bookMarkId){
		
		System.out.println("name: " + name);
		System.out.println("url: " + url);
		System.out.println("desc: " + desc);
		
		
		ModelAndView nextPage = new ModelAndView();
		
		String userId = (String)request.getSession().getAttribute("MEMBERID");
		String imgUrl = null;

		if(file != null){
			if(!file.getOriginalFilename().equals("")){
				String path = request.getSession().getServletContext().getRealPath("/users/img/")+"/" + userId + "/bookmark/";
				new FileWriter().writeFile(file, path, file.getOriginalFilename());
				imgUrl = "users/img/" + userId + "/bookmark/" + file.getOriginalFilename();
			}
		}
		
		BookMark bookMark = new BookMark(bookMarkId, name, url, desc, "", "", 0, 0, imgUrl, 0);
		new IndividualPageServiceImpl().modifyMark(bookMark);
		
		JSONObject jobj = new JSONObject();
		jobj.put("imgUrl", imgUrl);
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		return nextPage;
	}

	@RequestMapping("/deleteMark")
	public ModelAndView deleteMark(HttpServletRequest request,
			HttpServletResponse response, Img img, @RequestParam(value="bookmarkId")int bookMarkId){
		ModelAndView nextPage = new ModelAndView();
		
		new IndividualPageServiceImpl().deleteIcon(bookMarkId);
		
		request.setAttribute("result", Boolean.toString(true));
		nextPage.setViewName("result");
		return nextPage;
	}
	
	@RequestMapping("/arrange")
	public ModelAndView arrange(HttpServletRequest request,
								@RequestParam(value="location")String location){
		ModelAndView nextPage = new ModelAndView();
		
		JSONObject json = (JSONObject)JSONSerializer.toJSON(location);

		JSONObject result=null;
		
		for(int i=0;i<json.size();i++){
			result=(JSONObject)json.get(String.valueOf(i));

			int bookMarkId=Integer.parseInt(result.get("id").toString());
			int posX=Integer.parseInt(result.get("row").toString());
			int posY=Integer.parseInt(result.get("col").toString());
			BookMark bookMark=new BookMark(bookMarkId,"","","","","",posX,posY);
			new IndividualPageServiceImpl().arrangeIcon(bookMark);
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("location", location);
		
		request.setAttribute("result", jobj);
		nextPage.setViewName("result");
		return nextPage;
	}

}

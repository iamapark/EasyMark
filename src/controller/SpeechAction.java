package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.SpeechServiceImpl;

@Controller
public class SpeechAction {

	@RequestMapping("/speech")
	public ModelAndView speech(HttpServletRequest request, @RequestParam(value="speech")String speech,
														   @RequestParam(value="callback")String callback){
		ModelAndView nextPage = new ModelAndView();
		String deStr, data = null;
		String result = null, q = null, w = null;
		boolean flag = false;
		
		try {
			deStr = URLDecoder.decode(speech, "utf-8");
			flag = true;
		} catch (UnsupportedEncodingException e) {
			deStr = "인간의 언어가 아닙니다.";
		}

		System.out.println("speech: " + deStr);
		
		if(deStr.contains("추가")){
			switch(deStr){
			case "북마크 추가": case "북마크추가": case "북마크에 추가":
			case "즐겨찾기 추가": case "즐겨찾기추가":
			case "현재 페이지 추가":
			case "현재 페이지 즐겨찾기 추가":
				result = "1";
				break;
			default:
				result = "false";
			}
		}else if(deStr.contains("접속")){
			String c[] = deStr.split("에");
			if(c.length>1){
				result = "2";
				w = c[0];
				q = new SpeechServiceImpl().getSearchEngine(c[0]);
			}else{
				c = deStr.split("의");
				if(c.length>1){
					result = "2";
					w = c[0];
					q = new SpeechServiceImpl().getSearchEngine(c[0]);
				}else
					result = "false";
			}
		}else if(deStr.contains("검색")){
			result = "3";
		}else{
			result = "false"; 
			   
		}
		
		/**
		r: 기능 구분=> 1-즐겨찾기 추가, 2-웹 페이지 접속, 3-검색
		q: 웹 페이지 접속 시 url*/
		data = callback + "({\"result\" : \"" + Boolean.toString(flag) + "\", \"r\": \"" + result +  "\", \"q\": \"" + q +  "\", \"w\": \"" + w +  "\"})";
		
		request.setAttribute("result", data);
		nextPage.setViewName("result");
		return nextPage;
	}
}

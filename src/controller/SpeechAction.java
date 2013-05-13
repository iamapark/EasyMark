package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dto.Img;

@Controller
public class SpeechAction {

	@RequestMapping("/speech")
	public ModelAndView speech(HttpServletRequest request, @RequestParam(value="speech",required=false)String speech){
		ModelAndView nextPage = new ModelAndView();
		
		System.out.println("speech: " + speech);
		
		nextPage.setViewName("result");
		return nextPage;
	}
}

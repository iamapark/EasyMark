/**
 * 개인 페이지 구현 클래스입니다.
 * @author 박건준
 */

package service;

import java.util.ArrayList;
import java.util.HashMap;

import dao.BookMarkDAO;
import dto.BookMark;
import dto.Design;

public class IndividualPageServiceImpl implements IndividualPageServiceIF {

	
	@Override
	public void addBookMark(BookMark bookMark) {
		BookMarkDAO.getInstance().addBookMark(bookMark);
		
	}
	
	@Override
	public ArrayList<BookMark> bookMarkList(String userId) {
		ArrayList<BookMark> bookMarkList=null;
		bookMarkList=BookMarkDAO.getInstance().bookMarkList(userId);
		return bookMarkList;
	}


	@Override
	public int bookMarkPosx(String userId) {
		int posx=0;
		posx=BookMarkDAO.getInstance().bookMarkPosx(userId);
		return posx;
	}
	
	@Override
	public int bookMarkPosy(HashMap<String, Object> pos) {
		int posy=0;
		posy=BookMarkDAO.getInstance().bookMarkPosy(pos);
		return posy;
	}
	
	@Override
	public void arrangeIcon(BookMark bookMark) {
		BookMarkDAO.getInstance().arrangeIcon(bookMark);
		
	}
	public void deleteIcon(int bookMarkId){
		BookMarkDAO.getInstance().deleteIcon(bookMarkId);
	}
	public BookMark getBookMark(int bookMarkId){
		BookMark bookmark=new BookMark();
		bookmark=(BookMark)BookMarkDAO.getInstance().getBookMark(bookMarkId);
		return bookmark;
	}
	public void modifyMark(BookMark bookMark){
		BookMarkDAO.getInstance().modifyMark(bookMark);
	}

	
	@Override
	public void changeDesign(String userId, Design design) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void informEasyMark(String userId) {
		// TODO Auto-generated method stub
		
	}
	



	

}

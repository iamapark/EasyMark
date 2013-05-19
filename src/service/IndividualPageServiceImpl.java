/**
 * 개인 페이지 구현 클래스입니다.
 * @author 박건준
 */

package service;

import java.util.ArrayList;
import java.util.HashMap;

import dao.BookMarkDAO;
import dao.BookMarkListDAO;
import dao.CategoryDAO;
import dto.BookMark;
import dto.Category;
import dto.Design;

public class IndividualPageServiceImpl implements IndividualPageServiceIF {

	
	@Override
	public int addBookMark(BookMark bookMark) {
		return BookMarkDAO.getInstance().addBookMark(bookMark);
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

	//user의 category insert 
	public int addCategory(Category category){
		return CategoryDAO.getInstance().addCategory(category);
	}
	//user의 category delete
		public void deleteCategory(BookMark bookmark){
			BookMarkDAO.getInstance().deleteCategory(bookmark);
		}
	//user의 categoryList
	public ArrayList<Category> categoryList(String userId){
		return CategoryDAO.getInstance().categoryList(userId);
	}
	public boolean isExistCategory(String categoryName){
		boolean flag=false;
		flag=CategoryDAO.getInstance().isExistCategory(categoryName);
		return flag;
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

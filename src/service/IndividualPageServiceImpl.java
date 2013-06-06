/**
 * 개인 페이지 구현 클래스입니다.
 * @author 박건준
 */

package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.BookMarkDAO;
import dao.BookMarkListDAO;
import dao.CategoryDAO;
import dto.BookMark;
import dto.Category;
import dto.Design;
import dto.ForBookMarkList;

public class IndividualPageServiceImpl implements IndividualPageServiceIF {

	
	@Override
	public int addBookMark(BookMark bookMark) {
		return BookMarkDAO.getInstance().addBookMark(bookMark);
	}
	
	@Override
	public ArrayList<BookMark> bookMarkList(ForBookMarkList forListData) {
		ArrayList<BookMark> bookMarkList=null;
		bookMarkList=BookMarkDAO.getInstance().bookMarkList(forListData);
		return bookMarkList;
	}
	@Override
	public ArrayList<BookMark> bookMarkList(HashMap<String, Object> bookMarkInfo) {
		ArrayList<BookMark> bookMarkList=null;
		bookMarkList=BookMarkDAO.getInstance().bookMarkList(bookMarkInfo);
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
	public void deleteCategory(HashMap<String, Object> category){
		CategoryDAO.getInstance().deleteCategory(category);
	}
	//user의 categoryList
	public ArrayList<Category> categoryList(String userId){
		return CategoryDAO.getInstance().categoryList(userId);
	}
	//해당 카테고리 유무
	public boolean isExistCategory(HashMap<String, Object> categoryInfo){
		boolean flag=false;
		flag=CategoryDAO.getInstance().isExistCategory(categoryInfo);
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

	public void deleteIcons(ArrayList<Integer> selectedIdList) {
		BookMarkDAO.getInstance().deleteIcons(selectedIdList);
	}

	public void increaseFrequency(String bookmarkId) {
		BookMarkDAO.getInstance().increaseFrequency(bookmarkId);
	}
	public ArrayList<BookMark> listByCategory(HashMap<String, Object> category){
		ArrayList<BookMark> categoryList=null;
		categoryList=BookMarkListDAO.getInstance().listByCategory(category);
		return categoryList;
	}

	public ArrayList<Category> getCategoryList(String userId) {
		return CategoryDAO.getInstance().getCategoryList(userId);
	}

	public void deleteCategory(int categoryId) {
		//CategoryDAO.getInstance().deleteCategory(categoryId);
	}

	@Override
	public ArrayList<BookMark> bookMarkList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getParentId(int categoryId) {
		return CategoryDAO.getInstance().getParentId(categoryId);
	}

	public void modifyCategory(Category category) {
		CategoryDAO.getInstance().modifyCategory(category);
	}

	public void deleteBookMarkCategory(List target) {

		// 또는 bookmark 테이블에서 category가 categoryId인 데이터를 지운다.
		BookMarkDAO.getInstance().deleteBookMarkCategory(target);

		// bookmark_category 테이블에서 category_id가 categoryId인 데이터를 지운다.
		CategoryDAO.getInstance().deleteCategory(target);
	}

	public int addMark(Category category) {
		return BookMarkDAO.getInstance().addMark(category);
	}

	public ArrayList<Integer> getDeleteTargetList(String table, int categoryId) {
		if(table.equals("bookmarkCategory")){
			return CategoryDAO.getInstance().getDeleteTargetListCategory(categoryId);
		}else{
			return BookMarkDAO.getInstance().getDeleteTargetListBookmark(categoryId);
		}
	}

}

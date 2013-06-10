/**
 * 개인 페이지 구현 클래스입니다.
 * @author 박건준
 */

package service;

import java.util.ArrayList;
import java.util.List;

import dao.BookMarkDAO;
import dao.CategoryDAO;
import dto.BookMark;
import dto.Category;
import dto.ForBookMarkList;
import dto.Position;

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
	public void arrangeIcon(BookMark bookMark) {
		BookMarkDAO.getInstance().arrangeIcon(bookMark);
		
	}
	
	@Override
	public void deleteIcon(int bookMarkId){
		BookMarkDAO.getInstance().deleteIcon(bookMarkId);
	}
	
	@Override
	public BookMark getBookMark(int bookMarkId){
		BookMark bookmark=new BookMark();
		bookmark=(BookMark)BookMarkDAO.getInstance().getBookMark(bookMarkId);
		return bookmark;
	}
	
	@Override
	public void modifyMark(BookMark bookMark){
		BookMarkDAO.getInstance().modifyMark(bookMark);
	}

	//user의 category insert
	@Override
	public int addCategory(Category category){
		return CategoryDAO.getInstance().addCategory(category);
	}
	
	@Override
	public void deleteIcons(ArrayList<Integer> selectedIdList) {
		BookMarkDAO.getInstance().deleteIcons(selectedIdList);
	}

	@Override
	public void increaseFrequency(String bookmarkId) {
		BookMarkDAO.getInstance().increaseFrequency(bookmarkId);
	}
	
	@Override
	public ArrayList<Category> getCategoryList(String userId) {
		return CategoryDAO.getInstance().getCategoryList(userId);
	}

	@Override
	public int getParentId(int categoryId) {
		return CategoryDAO.getInstance().getParentId(categoryId);
	}

	@Override
	public void modifyCategory(Category category) {
		CategoryDAO.getInstance().modifyCategory(category);
	}

	@Override
	public void deleteBookMarkCategory(List target) {

		// 또는 bookmark 테이블에서 category가 categoryId인 데이터를 지운다.
		BookMarkDAO.getInstance().deleteBookMarkCategory(target);

		// bookmark_category 테이블에서 category_id가 categoryId인 데이터를 지운다.
		CategoryDAO.getInstance().deleteCategory(target);
	}

	@Override
	public int addMark(Category category) {
		return BookMarkDAO.getInstance().addMark(category);
	}

	@Override
	public ArrayList<Integer> getDeleteTargetList(String table, int categoryId) {
		if(table.equals("bookmarkCategory")){
			return CategoryDAO.getInstance().getDeleteTargetListCategory(categoryId);
		}else{
			return BookMarkDAO.getInstance().getDeleteTargetListBookmark(categoryId);
		}
	}

	@Override
	public ArrayList<Position> bookMarkPos(Category category) {
		return BookMarkDAO.getInstance().bookMarkPos(category);
	}
	
	@Override
	public ArrayList<Category> categoryList(String userId){
		return CategoryDAO.getInstance().categoryList(userId);
	}

}

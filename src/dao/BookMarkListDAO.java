package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.BookMark;

public class BookMarkListDAO {
	private static BookMarkListDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static BookMarkListDAO getInstance() {
		if (instance == null)
			instance = new BookMarkListDAO();
		return instance;
	}

	private BookMarkListDAO() {
		sqlMapper = DAOParser.getParser();
	}

	public ArrayList<BookMark> viewBookMarkList(String userId) {

		ArrayList<BookMark> viewBookMarkList = null;

		try {
			viewBookMarkList = (ArrayList<BookMark>) sqlMapper.queryForList(
					"viewBookMarkList", userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return viewBookMarkList;
	}

	public void deleteBookMarkList(int bookMarkId) {
		try {
			sqlMapper.delete("deleteBookMarkList", bookMarkId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<BookMark> bookMarkByFrequency(int bookMarkId){
		
		ArrayList<BookMark> bookMarkByFrequency =null;
		
		try {
			bookMarkByFrequency = (ArrayList<BookMark>) sqlMapper.queryForList("bookMarkByFrequency", bookMarkId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookMarkByFrequency;
		
	}
	public ArrayList<String> categoryList(String userId){
		
		ArrayList<String> categoryList = null;
		try {
			categoryList=(ArrayList<String>)sqlMapper.queryForList("categoryList", userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}
	/*
	 * public void updateBookMarkList(BookMark bookmark){
	 * 
	 * try { sqlMapper.update("updateBookMarkList", bookmark);
	 * System.out.println("bookmark"+bookmark); } catch (SQLException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 */

}

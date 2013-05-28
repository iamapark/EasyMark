package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.BookMark;

public class BookMarkDAO {
	private static BookMarkDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static BookMarkDAO getInstance(){
		if(instance == null)
			instance = new BookMarkDAO();
		return instance;
	}

	private BookMarkDAO(){
		sqlMapper = DAOParser.getParser();
	}
	public int addBookMark(BookMark bookMark) {
		int maxBookmarkId = 0;
		try {
			maxBookmarkId = (Integer)sqlMapper.insert("addBookMark", bookMark);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxBookmarkId;
	}
	public ArrayList<BookMark> bookMarkList(String userId){
		ArrayList<BookMark> bookMarkList=null;
		try {
			bookMarkList=(ArrayList<BookMark>)sqlMapper.queryForList("bookMarkList",userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookMarkList;
	}
	public int bookMarkPosx(String userId){
		int posx=0;
		try {
			posx=(Integer)sqlMapper.queryForObject("bookMarkPosx",userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posx;
	}
	
	public int bookMarkPosy(HashMap<String, Object> pos){
		int posy=0;
		
		try {
			posy=(Integer)sqlMapper.queryForObject("bookMarkPosy",pos);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return posy;
	}
	
	public void arrangeIcon(BookMark bookMark){
		try {
			sqlMapper.update("arrangeIcon",bookMark);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteIcon(int bookMarkId){
		try {
			sqlMapper.delete("deleteIcon", bookMarkId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BookMark getBookMark(int bookMarkId){
		BookMark bookmark = new BookMark();
		
		try {
			bookmark=(BookMark)sqlMapper.queryForObject("getBookMark", bookMarkId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookmark;
	}
	
	public void modifyMark(BookMark bookMark){
		try {
			sqlMapper.update("modifyMark", bookMark);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//카테고리 북마크 삭제
	public void deleteCategory(BookMark bookmark ){
		try {
			sqlMapper.delete("deleteCategory",bookmark);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteIcons(ArrayList<Integer> selectedIdList) {
		try {
			sqlMapper.delete("deleteIcons", selectedIdList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

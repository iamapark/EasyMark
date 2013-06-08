package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.BookMark;
import dto.Category;
import dto.ForBookMarkList;
import dto.Position;

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
	public ArrayList<BookMark> bookMarkList(ForBookMarkList forListData){
		ArrayList<BookMark> bookMarkList = null;

		try {
			bookMarkList = (ArrayList<BookMark>)sqlMapper.queryForList("bookMarkList", forListData);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookMarkList;
	}
	public ArrayList<BookMark> bookMarkList(HashMap<String, Object> bookMarkInfo){
		ArrayList<BookMark> bookMarkList=null;
		try {
			bookMarkList=(ArrayList<BookMark>)sqlMapper.queryForList("bookMarkListByCategory",bookMarkInfo);
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
		System.out.println("arrangeIcon");
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
	
	public void deleteIcons(ArrayList<Integer> selectedIdList) {
		try {
			sqlMapper.delete("deleteIcons", selectedIdList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void increaseFrequency(String bookmarkId) {
		try {
			sqlMapper.queryForObject("increaseFrequency", bookmarkId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBookMarkCategory(List target) {
		try {
			sqlMapper.delete("deleteBookMarkCategory", target);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int addMark(Category category) {
		int maxBookmarkId = 0;
		try{
			maxBookmarkId = (Integer) sqlMapper.insert("addBookmark", category);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return maxBookmarkId;
	}

	public ArrayList<Integer> getDeleteTargetListBookmark(int categoryId) {
		ArrayList<Integer> result = null;
		
		try{
			result = (ArrayList<Integer>)sqlMapper.queryForList("getDeleteTargetListBookmark", categoryId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<Position> bookMarkPos(Category category) {
		ArrayList<Position> list = null;
		
		try{
			list = (ArrayList<Position>)sqlMapper.queryForList("bookMarkPos", category);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}
	
}

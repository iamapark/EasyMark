package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.Category;

public class CategoryDAO {
	private static CategoryDAO instance = null;
	private SqlMapClient sqlMapper = null;
	
	public static CategoryDAO getInstance(){
		if(instance == null)
			instance = new CategoryDAO();
		return instance;
	}
	
	private CategoryDAO(){
		sqlMapper = DAOParser.getParser();
	}
	public int addCategory(Category category){
		int maxCategoryId = 0;
		try {
			maxCategoryId = (Integer)sqlMapper.insert("addCategory", category);
			category.setCategoryId(maxCategoryId);
			sqlMapper.insert("addBookmark", category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxCategoryId;
	}
	
	public boolean isExistCategory(HashMap<String, Object> categoryInfo){
		boolean flag=false;
		Category category=new Category();
		try {
			category=(Category)sqlMapper.queryForObject("isExistCategory", categoryInfo);
			if(category!=null){
				System.out.println("categoryName 겹친다 true 떠야대");
				flag=true;
				//왜 겹치는 카테고리 정보가 안들어가지?
				System.out.println("겹치는 카테고리 네임 :"+category.getCategoryName());
				System.out.println("겹치는 카테고리 id :"+category.getCategoryId());
				System.out.println("겹치는 카테고리 유저 네임 :"+category.getUserId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public ArrayList<Category> categoryList(String userId){
		ArrayList<Category> categoryList = new ArrayList<>();
		try {
			categoryList=(ArrayList<Category>)sqlMapper.queryForList("categoryList", userId);
			System.out.println("categoryList.size() :"+categoryList.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}
	//카테고리 북마크 삭제
	public void deleteCategory(HashMap<String, Object> category){
		try {
			sqlMapper.delete("deleteCategory",category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Category> getCategoryList(String userId) {
		ArrayList<Category> categoryList = new ArrayList<>();
		
		try{
			categoryList = (ArrayList<Category>)sqlMapper.queryForList("getCategoryList", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categoryList;
	}

	public void deleteCategory(int categoryId) {
		try {
			sqlMapper.delete("deleteBookmarkCategory",categoryId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getParentId(int categoryId) {
		int result = 0;
		
		try{
			result = (Integer) sqlMapper.queryForObject("getParentId", categoryId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
}

package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxCategoryId;
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

	public void deleteCategory(List target) {
		try {
			sqlMapper.delete("deleteBookmarkCategory",target);
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

	public void modifyCategory(Category category) {
		try{
			sqlMapper.update("modifyCategory", category);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getDeleteTargetListCategory(int categoryId) {
		ArrayList<Integer> result = null;
		
		try{
			result = (ArrayList<Integer>)sqlMapper.queryForList("getDeleteTargetListCategory", categoryId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String getCategoryName(int categoryId){
		String name = "";
		
		try{
			name = (String) sqlMapper.queryForObject("getCategoryName", categoryId);
		}catch(SQLException e){
			e.printStackTrace();
		}

		return name;
	}
	
}

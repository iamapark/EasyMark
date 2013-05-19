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
			sqlMapper.insert("addCategory", category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxCategoryId;
	}
	
	public boolean isExistCategory(String categoryName){
		boolean flag=false;
		Category category=null;
		try {
			category=(Category)sqlMapper.queryForObject("isExistCategory", categoryName);
			if(category!=null){
				flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public ArrayList<Category> categoryList(String userId){
		ArrayList<Category> categoryList=new ArrayList<>();
		try {
			categoryList=(ArrayList<Category>)sqlMapper.queryForList("categoryList", userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categoryList;
	}
	


}

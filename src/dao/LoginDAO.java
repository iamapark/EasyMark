package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.Login;
import dto.Member;

public class LoginDAO {
	private static LoginDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static LoginDAO getInstance(){
		if(instance == null)
			instance = new LoginDAO();
		return instance;
	}

	private LoginDAO(){
		sqlMapper = DAOParser.getParser();
	}
	
	public boolean login(Login login) {
		String result = null;
		boolean flag = false;
		try{
			result = (String)sqlMapper.queryForObject("adminLogin", login);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(result != null) flag = true;
		return flag;
	}

	
}

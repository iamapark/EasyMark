package dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.Login;
import dto.Member;

public class RegisterDAO {

	private static RegisterDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static RegisterDAO getInstance() {
		if (instance == null)
			instance = new RegisterDAO();
		return instance;
	}

	private RegisterDAO() {
		sqlMapper = DAOParser.getParser();
	}
	
	public void registerMember(Member member) {
		try{
			sqlMapper.insert("registerMember", member);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void registerMemberInfo(Member member) {
		try{
			sqlMapper.insert("registerMemberInfo", member);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void registerDesign(Member member) {
		try{
			sqlMapper.insert("registerDesign", member);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public boolean checkId(String userId) {
		String checkId = null;
		boolean flag = false;
		try{
			checkId = (String)sqlMapper.queryForObject("checkId", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(checkId != null)
			flag = true;
		
		return flag;
	}

	public boolean login(Login login) {
		String userId = null;
		boolean flag = false;
		try{
			userId = (String)sqlMapper.queryForObject("login", login);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(userId != null)
			flag = true;
		
		return flag;
	}

	public String getDesignType(String userId) {
		String type = null;
		try{
			type = (String)sqlMapper.queryForObject("getDesignType", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return type;
	}

}

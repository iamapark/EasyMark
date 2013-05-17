package dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SpeechDAO {
	private static SpeechDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static SpeechDAO getInstance(){
		if(instance == null)
			instance = new SpeechDAO();
		return instance;
	}

	private SpeechDAO(){
		sqlMapper = DAOParser.getParser();
	}

	public String getSearchEngine(String speech) {
		String result = null;
		
		try{
			result = (String)sqlMapper.queryForObject("getSearchEngine", speech);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	
}

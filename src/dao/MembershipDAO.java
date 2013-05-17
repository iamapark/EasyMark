package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.Member;
import dto.MemberInfo;

public class MembershipDAO {
	private static MembershipDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static MembershipDAO getInstance(){
		if(instance == null)
			instance = new MembershipDAO();
		return instance;
	}

	private MembershipDAO(){
		sqlMapper = DAOParser.getParser();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Member> fillMemberTable() {
		ArrayList<Member> memberList = null;
		
		try{
			memberList = (ArrayList<Member>)sqlMapper.queryForList("fillMemberTable");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return memberList;
	}

	public MemberInfo getMemberInfo_admin(String userId) {
		userId = userId.trim();
		MemberInfo m = null;
		
		try{
			m = (MemberInfo)sqlMapper.queryForObject("getMemberInfo_admin", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		System.out.println(m);
		return m;
	}
}

package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
		return m;
	}

	public void deleteMembers(ArrayList<String> idList) {
		try{
			sqlMapper.delete("deleteMemberInfo", idList);
			sqlMapper.delete("deleteBookMark", idList);
			sqlMapper.delete("deleteDesign", idList);
			sqlMapper.delete("deleteMember", idList);
			sqlMapper.update("leaveMember", idList);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void deleteMembers(String userId) {
		ArrayList<String> idList = new ArrayList<String>();
		idList.add(userId);
		try{
			sqlMapper.delete("deleteMemberInfo", idList);
			sqlMapper.delete("deleteBookMark", idList);
			sqlMapper.delete("deleteDesign", idList);
			sqlMapper.delete("deleteMember", idList);
			sqlMapper.update("leaveMember", idList);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

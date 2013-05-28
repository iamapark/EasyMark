package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.Count;
import dto.DashboardCount;
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
			sqlMapper.update("deleteLoginInfo", idList);
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
			sqlMapper.update("deleteLoginInfo", idList);
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Count> getRegisterCount(String selectedMonth) {
		ArrayList<Count> c = new ArrayList<Count>();
		
		try{
			c = (ArrayList<Count>)sqlMapper.queryForList("getRegisterCount", selectedMonth);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return c;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Count> getLoginCounterHourly() {
		ArrayList<Count> c = new ArrayList<Count>();
		
		try{
			c = (ArrayList<Count>)sqlMapper.queryForList("getLoginCounterHourly");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return c;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Count> getTotalStatistics() {
		ArrayList<Count> i = new ArrayList<Count>();
		
		try{
			i = (ArrayList<Count>)sqlMapper.queryForList("getTotalStatistics");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return i;
	}

	public DashboardCount getDashboardCount() {
		DashboardCount i = new DashboardCount();
		
		try{
			i = (DashboardCount)sqlMapper.queryForObject("getDashboardCount");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return i;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Member> getLoginMembersInfoList(ArrayList<String> ar) {
		ArrayList<Member> loginMemberInfoList = new ArrayList<Member>();
		
		try{
			loginMemberInfoList = (ArrayList<Member>)sqlMapper.queryForList("getLoginMembersInfoList", ar);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return loginMemberInfoList;
	}

	public Member getLoginMemberInfo(String userId) {
		Member m = new Member();
		
		try{
			m = (Member)sqlMapper.queryForObject("getLoginMemberInfo", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return m;
	}
}

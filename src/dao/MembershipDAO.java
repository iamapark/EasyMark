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
	
	/**
	 * EasyMark에 가입해 있는 회원들에 관한 정보를 불러오는 메소드
	 * @return ArrayList<Member> 회원 정보 데이터가 담겨 있음
	 */
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

	/**
	 * 회원 한 명에 관한 정보를 불러오는 메소드
	 * @param String 회원 아이디
	 * @return MemberInfo 회원 정보 데이터가 담겨 있음
	 */
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

	/**
	 * 회원 여러 명을 한꺼번에 지우는 메소드
	 * 회원에 관한 모든 데이터를 지워야 하기 때문에 여러 테이블에 delete 문을 수행한다.
	 * @param ArrayList<String> 지우고자 하는 회원 아이디가 담겨 있는 리스트
	 */
	public void deleteMembers(ArrayList<String> idList) {
		try{
			sqlMapper.delete("deleteMemberInfo", idList);
			sqlMapper.delete("deleteBookMark", idList);
			sqlMapper.delete("deleteDesign", idList);
			sqlMapper.update("leaveMember", idList);
			sqlMapper.update("deleteLoginInfo", idList);
			sqlMapper.delete("deleteMemberCategory", idList);
			sqlMapper.delete("deleteMember", idList);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * 회원 한 명을 지우는 메소드
	 * 회원에 관한 모든 데이터를 지워야 하기 때문에 여러 테이블에 delete 문을 수행한다.
	 * @param String 지우고자 하는 회원 아이디
	 */
	public void deleteMembers(String userId) {
		ArrayList<String> idList = new ArrayList<String>();
		idList.add(userId);
		try{
			sqlMapper.delete("deleteMemberInfo", idList);
			sqlMapper.delete("deleteBookMark", idList);
			sqlMapper.delete("deleteDesign", idList);
			sqlMapper.update("leaveMember", idList);
			sqlMapper.update("deleteLoginInfo", idList);
			sqlMapper.delete("deleteMemberCategory", idList);
			sqlMapper.delete("deleteMember", idList);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * 월 별 가입자 수를 불러오는 메소드
	 * @param String 해당 월
	 * @return ArrayList<Count> 해당 월에 가입한 회원의 수가 담겨 있는 리스트
	 */
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

	/**
	 * 시간대 별 접속 회수를 불러오는 메소드
	 * @return ArrayList<Count> 각 시간대 별 접속 회수가 담겨 있는 리스트
	 */
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

	/**
	 * 총 접속 시간, 사용자 별 평균 접속 시간, 총 북마크 수, 사용자 별 평균 북마크 수를 불러온다.
	 * @return ArrayList<Count> 각 통계 자료가 담겨 있는 리스트
	 */
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

	/**
	 * 총 회원 수, 오늘 가입한 회원 수, 총 쪽지 개수를 불러온다.
	 * @return DashboardCount 각 통계 자료가 담겨 있는 변수
	 */
	public DashboardCount getDashboardCount() {
		DashboardCount i = new DashboardCount();
		
		try{
			i = (DashboardCount)sqlMapper.queryForObject("getDashboardCount");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return i;
	}

	/**
	 * 현재 로그인 중인 사용자들의 정보를 받아온다.
	 * @param ArrayList<String> 현재 로그인 중인 사용자들의 아이디
	 * @return ArrayList<Member> 사용자 정보가 담긴 리스트 
	 */
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

	public Member isEmail(String email) {
		Member m = null;
		
		try{
			m = (Member)sqlMapper.queryForObject("isEmail", email);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return m;
	}
}

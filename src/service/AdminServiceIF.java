package service;

import java.util.ArrayList;

import dto.Count;
import dto.DashboardCount;
import dto.Login;
import dto.Member;
import dto.MemberInfo;

/**
 * 관리자 서비스
 * @author 박진영
 * */
public interface AdminServiceIF {
	
	
	/**
	 * 로그인 메소드
	 * @param Login 로그인 정보가 담겨 있음
	 * @return true-로그인 성공, false-로그인 실패
	 */
	public boolean login(Login login); 
	
	/**
	 * EasyMark에 가입해 있는 회원들에 관한 정보를 불러오는 메소드
	 * @return ArrayList<Member> 회원 정보 데이터가 담겨 있음
	 */
	public ArrayList<Member> fillMemberTable();
	
	/**
	 * 회원 한 명에 관한 정보를 불러오는 메소드
	 * @param String 회원 아이디
	 * @return MemberInfo 회원 정보 데이터가 담겨 있음
	 */
	public MemberInfo getMemberInfo_admin(String userId);
	
	/**
	 * 회원 여러 명을 한꺼번에 지우는 메소드
	 * @param ArrayList<String> 지우고자 하는 회원 아이디가 담겨 있는 리스트
	 */
	public void deleteMembers(ArrayList<String> idList);
	
	/**
	 * 회원 한 명을 한꺼번에 지우는 메소드
	 * @param String 지우고자 하는 회원 아이디
	 */
	public void deleteMembers(String userId);
	
	/**
	 * 월 별 가입자 수를 불러오는 메소드
	 * @param String 해당 월
	 * @return ArrayList<Count> 해당 월에 가입한 회원의 수가 담겨 있는 리스트
	 */
	public ArrayList<Count> getRegisterCount(String selectedMonth);
	
	/**
	 * 시간대 별 접속 회수를 불러오는 메소드
	 * @return ArrayList<Count> 각 시간대 별 접속 회수가 담겨 있는 리스트
	 */
	public ArrayList<Count> getLoginCounterHourly();
	
	/**
	 * 총 접속 시간, 사용자 별 평균 접속 시간, 총 북마크 수, 사용자 별 평균 북마크 수를 불러온다.
	 * @return ArrayList<Count> 각 통계 자료가 담겨 있는 리스트
	 */
	public ArrayList<Count> getTotalStatistics();
	
	/**
	 * 총 회원 수, 오늘 가입한 회원 수, 총 쪽지 개수를 불러온다.
	 * @return DashboardCount 각 통계 자료가 담겨 있는 변수
	 */
	public DashboardCount getDashboardCount();
	
	/**
	 * 현재 로그인 중인 사용자들의 정보를 받아온다.
	 * @param ArrayList<String> 현재 로그인 중인 사용자들의 아이디
	 * @return ArrayList<Member> 사용자 정보가 담긴 리스트 
	 */
	public ArrayList<Member> getLoginMembersInfoList(ArrayList<String> ar);

}

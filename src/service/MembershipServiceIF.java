package service;

import java.util.ArrayList;

import dto.BookMark;
import dto.Design;
import dto.Login;
import dto.Member;

/**
 * 회원 관리 서비스
 * @author 박진영
 * */
public interface MembershipServiceIF {
	
	/**
	 * member 테이블에 사용자 정보를 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerMember(Member member);
	
	/**
	 * member_info 테이블에 사용자 정보를 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerMemberInfo(Member member);
	
	/**
	 * design 테이블에 사용자 정보를 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerDesign(Member member);
	
	/**
	 * register_time에 가입 시간을 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerRegisterTime(Member member);
	
	/**
	 * 인자로 넘겨받은 아이디가 이미 존재하는지 검사하는 메소드
	 * @param String 검사하고자 하는 아이디
	 * @return true-이미 아이디가 존재, false-아이디 없음
	 */
	public boolean checkId(String userId);

	/**
	 * 일반 계정으로 로그인할 때 사용되는 메소드
	 * @param Login 로그인 정보가 담겨있는 변수
	 * @return boolean true-로그인 성공, false-로그인 실패
	 */
	boolean login(Login login);

	/**
	 * 사용자의 디자인 타입을 불러오는 메소드
	 * @param String 해당 아이디
	 * @return String 디자인 타입
	 */
	public String getDesignType(String userId);

	/**
	 * 사용자의 정보를 받아오는 메소드
	 * @param String 해당 아이디
	 * @return Member 회원 정보가 담긴 변수
	 */
	public Member getMemberInfo(String userId);

	/**
	 * 사용자가 자신의 디자인 타입을 바꿀 때 사용하는 메소드
	 * @param Design 디자인 정보가 담긴 변수
	 */
	public void changeDesign(Design design);

	/**
	 * 바탕화면 배경 이미지를 바꿀 때 사용하는 메소드
	 * @param Member 회원에 관한 정보가 담긴 변수
	 */
	public void updateBgImg(Member m);
	
	/**
	 * 미투데이 계정으로 회원가입할 때 사용하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	boolean registerMe2DayMember(Member member);

	/**
	 * 미투데이 계정으로 로그인할 때 사용하는 메소드
	 * @param Login 로그인 정보가 담겨 있는 변수
	 * @return boolean true-로그인 성공, false-로그인 실패
	 */
	boolean me2DayLoginCheck(Login login);

	/**
	 * 회원 정보를 수정할 때 사용하는 메소드
	 * @param Member 회원에 관한 정보가 담긴 메소드
	 */
	public void updateMemberInfo(Member m);
	
	/**
	 * 로그인 할 때마다 login_time 테이블에 로그인 시간을 저장할 때 사용하는 메소드
	 * @param String 해당 아이디
	 */
	public void loginCount(String userId);
	
	/**
	 * 로그아웃 할 때마다 login_time 테이블에 로그아웃 시간을 저장할 때 사용하는 메소드
	 * @param String 해당 아이디
	 */
	public void logoutCount(String userId);

	/**
	 * 사용자가 저장한 북마크 리스트를 불러오는 메소드
	 * @param String 해당 아이디
	 * @return ArrayList<BookMark> 북마크 정보가 담긴 리스트
	 */
	public ArrayList<BookMark> viewBookMarkList(String userId);

	/**
	 * 해당 이메일이 DB에 존재하는지 검사
	 * @param String 이메일 주소
	 * @return Member 회원 정보
	 */
	Member isEmail(String email);

	/**
	 * 해당 이메일이 DB에 존재하는지 검사
	 * @param Member 회원 정보
	 * @return boolean true-존재, false-없음
	 */
	public void updatePassword(Member m);
	
}

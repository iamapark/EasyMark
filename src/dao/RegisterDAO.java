package dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.Design;
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
	
	/**
	 * member 테이블에 사용자 정보를 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerMember(Member member) {
		try{
			sqlMapper.insert("registerMember", member);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * member_info 테이블에 사용자 정보를 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerMemberInfo(Member member) {
		try{
			sqlMapper.insert("registerMemberInfo", member);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * design 테이블에 사용자 정보를 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerDesign(Member member) {
		try{
			sqlMapper.insert("registerDesign", member);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * 인자로 넘겨받은 아이디가 이미 존재하는지 검사하는 메소드
	 * @param String 검사하고자 하는 아이디
	 * @return true-이미 아이디가 존재, false-아이디 없음
	 */
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

	/**
	 * 일반 계정으로 로그인할 때 사용되는 메소드
	 * @param Login 로그인 정보가 담겨있는 변수
	 * @return boolean true-로그인 성공, false-로그인 실패
	 */
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

	/**
	 * 사용자의 디자인 타입을 불러오는 메소드
	 * @param String 해당 아이디
	 * @return String 디자인 타입
	 */
	public String getDesignType(String userId) {
		String type = null;
		try{
			type = (String)sqlMapper.queryForObject("getDesignType", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return type;
	}

	/**
	 * 사용자의 정보를 받아오는 메소드
	 * @param String 해당 아이디
	 * @return Member 회원 정보가 담긴 변수
	 */
	public Member getMemberInfo(String userId) {
		Member m = null;
		
		try{
			m = (Member)sqlMapper.queryForObject("getMemberInfo", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return m;
	}

	/**
	 * 회원 정보를 수정할 때 사용하는 메소드
	 * @param Member 회원에 관한 정보가 담긴 메소드
	 */
	public void updateMemberInfo(Member m) {
		try{
			sqlMapper.update("updateMemberInfo", m);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 사용자가 자신의 디자인 타입을 바꿀 때 사용하는 메소드
	 * @param Design 디자인 정보가 담긴 변수
	 */
	public void changeDesign(Design design) {
		try{
			sqlMapper.update("changeDesign", design);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * 바탕화면 배경 이미지를 바꿀 때 사용하는 메소드
	 * @param Member 회원에 관한 정보가 담긴 변수
	 */
	public void updateBgImg(Member m) {
		try{
			sqlMapper.update("updateBgImg", m);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * 미투데이 계정으로 회원가입할 때 사용하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public boolean registerMe2DayMember(Member member) {
		boolean flag = true;
		
		if (isExistMe2DayMember(member.getUserId())) {
			System.out.println("이미 존재하는 미투데이 계정입니다.");
			flag = false;
		}

		if (flag) {
			try {
				sqlMapper.insert("registerMember", member);
				registerMemberInfo(member);
				registerDesign(member);
				registerRegisterTime(member);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return flag;
	}

	/**
	 * 미투데이 계정으로 로그인할 때 사용하는 메소드
	 * @param Login 로그인 정보가 담겨 있는 변수
	 * @return boolean true-로그인 성공, false-로그인 실패
	 */
	public boolean me2DayLoginCheck(Login login) {
		boolean flag = false;
		Login result = null;

		try {
			result = (Login)sqlMapper.queryForObject("me2DayLoginCheck", login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(result != null) flag = true;

		return flag;
	}
	
	/**
	 * 미투데이 계정으로 가입하기 전 해당 계정이 이미 가입되어 있는지 검사하는 메소드
	 * @param String 검사하려는 미투데이 아이디
	 * @return boolean true-아이디가 이미 존재, false-아이디 없음
	 */
	private boolean isExistMe2DayMember(String userId) {
		boolean flag = false;
		Member member = null;

		try {
			member = (Member) sqlMapper.queryForObject("isExistMe2DayMember",
					userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (member != null)
			flag = true;

		return flag;
	}

	/**
	 * register_time에 가입 시간을 저장하는 메소드
	 * @param Member 사용자 정보가 담겨 있는 변수
	 */
	public void registerRegisterTime(Member member) {
		try{
			sqlMapper.insert("registerRegisterTime", member);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * 로그인 할 때마다 login_time 테이블에 로그인 시간을 저장할 때 사용하는 메소드
	 * @param String 해당 아이디
	 */
	public void loginCount(String userId) {
		try{
			sqlMapper.insert("loginCount", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * 로그아웃 할 때마다 login_time 테이블에 로그아웃 시간을 저장할 때 사용하는 메소드
	 * @param String 해당 아이디
	 */
	public void logoutCount(String userId) {
		try{
			sqlMapper.insert("logoutCount", userId);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}

package service;

import java.util.ArrayList;

import dto.BookMark;
import dto.Design;
import dto.Login;
import dto.Member;

public interface MembershipServiceIF {
	public void registerMember(Member member);

	public void registerMemberInfo(Member member);

	public void registerDesign(Member member);

	public boolean checkId(String userId);

	public boolean login(Login login);

	public String getDesignType(String userId);

	public Member getMemberInfo(String userId);

	public void changeDesign(Design design);

	public void updateBgImg(Member m);

	boolean registerMe2DayMember(Member member);

	boolean me2DayLoginCheck(Login login);


	public ArrayList<BookMark> viewBookMarkList(String userId);
	
}

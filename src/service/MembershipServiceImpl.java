package service;

import java.util.ArrayList;

import dao.BookMarkListDAO;
import dao.MembershipDAO;
import dao.RegisterDAO;
import dto.BookMark;
import dto.Design;
import dto.Login;
import dto.Member;

public class MembershipServiceImpl implements MembershipServiceIF {

	@Override
	public void registerMember(Member member) {
		RegisterDAO.getInstance().registerMember(member);
	}

	@Override
	public void registerMemberInfo(Member member) {
		RegisterDAO.getInstance().registerMemberInfo(member);
	}

	@Override
	public void registerDesign(Member member) {
		RegisterDAO.getInstance().registerDesign(member);
	}

	@Override
	public boolean checkId(String userId) {
		return RegisterDAO.getInstance().checkId(userId);
	}

	@Override
	public boolean login(Login login) {
		return RegisterDAO.getInstance().login(login);
	}

	@Override
	public String getDesignType(String userId) {
		return RegisterDAO.getInstance().getDesignType(userId);
	}

	@Override
	public Member getMemberInfo(String userId) {
		return RegisterDAO.getInstance().getMemberInfo(userId);
	}

	@Override
	public void updateMemberInfo(Member m) {
		RegisterDAO.getInstance().updateMemberInfo(m);
	}

	@Override
	public void changeDesign(Design design) {
		RegisterDAO.getInstance().changeDesign(design);
	}

	@Override
	public void updateBgImg(Member m) {
		RegisterDAO.getInstance().updateBgImg(m);
	}

	@Override
	public boolean registerMe2DayMember(Member member) {
		return RegisterDAO.getInstance().registerMe2DayMember(member);
	}

	@Override
	public boolean me2DayLoginCheck(Login login) {
		return RegisterDAO.getInstance().me2DayLoginCheck(login);
	}

	@Override
	public void registerRegisterTime(Member member) {
		RegisterDAO.getInstance().registerRegisterTime(member);
	}

	@Override
	public void loginCount(String userId) {
		RegisterDAO.getInstance().loginCount(userId);
	}

	@Override
	public ArrayList<BookMark> viewBookMarkList(String userId) {
		ArrayList<BookMark> bookMarkList = null;
		bookMarkList = BookMarkListDAO.getInstance().viewBookMarkList(userId);
		return bookMarkList;
	}

	@Override
	public void logoutCount(String userId) {
		RegisterDAO.getInstance().logoutCount(userId);
	}

	@Override
	public Member isEmail(String email) {
		return MembershipDAO.getInstance().isEmail(email);
	}

	@Override
	public void updatePassword(Member m) {
		MembershipDAO.getInstance().updatePassword(m);
	}

}

package service;

import java.util.ArrayList;

import dao.LoginDAO;
import dao.MembershipDAO;
import dto.Login;
import dto.Member;
import dto.MemberInfo;

public class AdminServiceImpl implements AdminServiceIF {

	@Override
	public boolean login(Login login) {
		return LoginDAO.getInstance().login(login);
	}

	public ArrayList<Member> fillMemberTable() {
		return MembershipDAO.getInstance().fillMemberTable();
	}

	public MemberInfo getMemberInfo_admin(String userId) {
		return MembershipDAO.getInstance().getMemberInfo_admin(userId);
	}

	public void deleteMembers(ArrayList<String> idList) {
		MembershipDAO.getInstance().deleteMembers(idList);
	}

	public void deleteMembers(String userId) {
		MembershipDAO.getInstance().deleteMembers(userId);
	}
}

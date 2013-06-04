package service;

import java.util.ArrayList;

import dao.LoginDAO;
import dao.MembershipDAO;
import dto.Count;
import dto.DashboardCount;
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

	public ArrayList<Count> getRegisterCount(String selectedMonth) {
		return MembershipDAO.getInstance().getRegisterCount(selectedMonth);
	}

	public ArrayList<Count> getLoginCounterHourly() {
		return MembershipDAO.getInstance().getLoginCounterHourly();
	}

	public ArrayList<Count> getTotalStatistics() {
		return MembershipDAO.getInstance().getTotalStatistics();
	}

	public DashboardCount getDashboardCount() {
		return MembershipDAO.getInstance().getDashboardCount();
	}

	public ArrayList<Member> getLoginMembersInfoList(ArrayList<String> ar) {
		return MembershipDAO.getInstance().getLoginMembersInfoList(ar);
	}

	public Member getLoginMemberInfo(String userId) {
		return MembershipDAO.getInstance().getLoginMemberInfo(userId);
	}
}
package service;

import java.util.ArrayList;

import dao.LoginDAO;
import dao.MembershipDAO;
import dto.Count;
import dto.DashboardCount;
import dto.Login;
import dto.Member;
import dto.MemberInfo;

/**
 * 관리자 서비스
 * @author 박진영
 * */
public class AdminServiceImpl implements AdminServiceIF {

	@Override
	public boolean login(Login login) {
		return LoginDAO.getInstance().login(login);
	}

	@Override
	public ArrayList<Member> fillMemberTable() {
		return MembershipDAO.getInstance().fillMemberTable();
	}

	@Override
	public MemberInfo getMemberInfo_admin(String userId) {
		return MembershipDAO.getInstance().getMemberInfo_admin(userId);
	}

	@Override
	public void deleteMembers(ArrayList<String> idList) {
		MembershipDAO.getInstance().deleteMembers(idList);
	}

	@Override
	public void deleteMembers(String userId) {
		MembershipDAO.getInstance().deleteMembers(userId);
	}

	@Override
	public ArrayList<Count> getRegisterCount(String selectedMonth) {
		return MembershipDAO.getInstance().getRegisterCount(selectedMonth);
	}

	@Override
	public ArrayList<Count> getLoginCounterHourly() {
		return MembershipDAO.getInstance().getLoginCounterHourly();
	}

	@Override
	public ArrayList<Count> getTotalStatistics() {
		return MembershipDAO.getInstance().getTotalStatistics();
	}

	@Override
	public DashboardCount getDashboardCount() {
		return MembershipDAO.getInstance().getDashboardCount();
	}

	@Override
	public ArrayList<Member> getLoginMembersInfoList(ArrayList<String> ar) {
		return MembershipDAO.getInstance().getLoginMembersInfoList(ar);
	}
}

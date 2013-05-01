package service;

import dao.RegisterDAO;
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

}

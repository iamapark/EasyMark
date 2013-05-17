package service;

import java.util.ArrayList;

import dto.Login;
import dto.Member;

public interface AdminServiceIF {
	public boolean login(Login login); 
	
	public ArrayList<Member> fillMemberTable();
}

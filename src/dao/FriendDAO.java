package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.BookMark;
import dto.BookMarkShip;
import dto.FriendStatus;
import dto.Friendship;
import dto.Member;
//import dto.Message;
import dto.User;

public class FriendDAO {
	private static FriendDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static FriendDAO getInstance() {
		if (instance == null)
			instance = new FriendDAO();
		return instance;
	}

	private FriendDAO() {
		sqlMapper = DAOParser.getParser();
	}

	private boolean isExist(String userId) {
		boolean flag = false;
		String result = null;
		try {
			result = (String) sqlMapper.queryForObject("isExist", userId);
			if (result != null)
				flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Member> getFriendList(Friendship friend) {
		ArrayList<Member> friendList = null;
		try {
			friendList = (ArrayList<Member>) sqlMapper.queryForList(
					"getFriendList", friend);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Member> getFriendListBar(Friendship friend) {
		ArrayList<Member> friendListBar = null;
		try {
			friendListBar = (ArrayList<Member>) sqlMapper.queryForList(
					"getFriendBar", friend);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendListBar;
	}

	public Member me2dayFriend(String me2FriendId) {
		Member me2friend = null;

		try {
			me2friend = (Member) sqlMapper.queryForObject("me2dayFriend", me2FriendId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return me2friend;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Member> getMemberById(User searchUser) {
		ArrayList<Member> resultList = null;

		try {
			// resultList = (ArrayList<FriendStatus>)
			// sqlMapper.queryForList("getMemberById", searchUser);
			resultList = (ArrayList<Member>) sqlMapper.queryForList(
					"getMemberById", searchUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}

	/*
	 * public FriendStatus getFriendStatus(FriendStatus friendStatus){
	 * //ArrayList<FriendStatus> resultList = new ArrayList<FriendStatus>();
	 * FriendStatus resultList = new FriendStatus(); try { //resultList =
	 * (ArrayList<FriendStatus>) sqlMapper.queryForList("getFriendStatus",
	 * friendStatus); resultList =
	 * (FriendStatus)sqlMapper.queryForObject("getFriendStatus", friendStatus);
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return resultList; }
	 */

	@SuppressWarnings("unchecked")
	public ArrayList<FriendStatus> getFriendStatus(FriendStatus friendStatus) {
		ArrayList<FriendStatus> resultList = new ArrayList<FriendStatus>();
		// FriendStatus resultList = new FriendStatus();
		try {
			resultList = (ArrayList<FriendStatus>) sqlMapper.queryForList(
					"getFriendStatus", friendStatus);
			// resultList =
			// (FriendStatus)sqlMapper.queryForObject("getFriendStatus",
			// friendStatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultList;
	}

	public void requestFriend(Friendship friendship) {
		try {
			sqlMapper.insert("insertFriend", friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void accept(Friendship friendship) {
		try {
			sqlMapper.update("accept", friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void reject(Friendship friendship) {
		try {
			sqlMapper.delete("reject", friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cancel(Friendship friendship) {
		try {
			sqlMapper.delete("cancel", friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteFriend(Friendship friendship) {
		try {
			sqlMapper.delete("deleteFriend", friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Member> takeFriendReqList(Friendship friendship) {
		ArrayList<Member> resultList = null;

		try {
			resultList = (ArrayList<Member>) sqlMapper.queryForList(
					"takeFriendReqList", friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Member> sendFriendReqList(Friendship friendship) {
		ArrayList<Member> resultList = null;

		try {
			resultList = (ArrayList<Member>) sqlMapper.queryForList(
					"sendFriendReqList", friendship);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultList;
	}

	
	/*public void bookMarkRecommand(BookMarkShip bookmarkship) {

		try {
			sqlMapper.insert("bookMarkRecommand", bookmarkship);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	// 북마크 셀렉트해서 친구한테 넘겨주기 전에 받으려고 하는 것
	public BookMark selectBookMark(int bookMarkId) {

		BookMark selectBookMarkList = null;

		try {
			selectBookMarkList = (BookMark) sqlMapper.queryForObject(
					"selectBookMark", bookMarkId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return selectBookMarkList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<BookMarkShip> recommendOutWeb(String userId) {
		ArrayList<BookMarkShip> outWebList = null;
		try {
			outWebList = (ArrayList<BookMarkShip>) sqlMapper.queryForList(
					"outWebList", userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outWebList;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<BookMarkShip> recommendInWeb(String userId) {
		ArrayList<BookMarkShip> inWebList = null;
		try {
			inWebList = (ArrayList<BookMarkShip>) sqlMapper.queryForList(
					"inWebList", userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inWebList;
	}

	public void recommendCancel(String bookMarkId) {
		try {
			sqlMapper.delete("bookMarkCancel", bookMarkId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Member getKey(String userId){
		Member memberKey = null;
		try {
			memberKey = (Member)sqlMapper.queryForObject("getKey", userId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberKey;

	}
	
	public boolean bookMarkExist(BookMarkShip bookmarkship){
		boolean flag = false;
		
		BookMarkShip bookmark = null;
		try {
			bookmark = (BookMarkShip)sqlMapper.queryForObject("bookMarkExist", bookmarkship);
			if(bookmark == null)
				flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	public void recommendSite(BookMarkShip bookmarkship) {
		try {
			sqlMapper.insert("bookMarkRecommand", bookmarkship);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}

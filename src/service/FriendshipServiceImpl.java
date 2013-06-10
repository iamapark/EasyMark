/*
 * 작성자: 신보미
 * 관리자: 신보미
 * */

package service;

import java.util.ArrayList;

import dao.FriendDAO;
import dao.MessageDAO;
import dto.BookMark;
import dto.BookMarkShip;
import dto.FriendStatus;
import dto.Friendship;
import dto.Member;
import dto.Message;
import dto.User;

public class FriendshipServiceImpl implements FriendshipServiceIF {

	@Override
	public ArrayList<Member> getFriendList(Friendship friend) {
		ArrayList<Member> friendList = null;
		friendList = FriendDAO.getInstance().getFriendList(friend);
		return friendList;
	}

	@Override
	public Member me2dayFriend(String me2FriendId) {
		Member me2FriendInfo = null;
		me2FriendInfo = FriendDAO.getInstance().me2dayFriend(me2FriendId);
		return me2FriendInfo;
	}

	@Override
	public ArrayList<Member> searchFriend(User searchUser) {
		ArrayList<Member> searchList = null;
		searchList = FriendDAO.getInstance().getMemberById(searchUser);
		return searchList;
	}

	@Override
	public ArrayList<FriendStatus> getFriendStatus(FriendStatus friendStatus) {
		ArrayList<FriendStatus> getFriendStatus = new ArrayList<FriendStatus>();
		getFriendStatus = FriendDAO.getInstance().getFriendStatus(friendStatus);
		return getFriendStatus;
	}

	@Override
	public ArrayList<Member> takeFriendReqList(Friendship friendship) {
		ArrayList<Member> takeFriendReqList = null;
		takeFriendReqList = FriendDAO.getInstance().takeFriendReqList(friendship);
		return takeFriendReqList;
	}

	@Override
	public ArrayList<Member> sendFriendReqList(Friendship friendship) {
		ArrayList<Member> sendFriendReqList = null;
		sendFriendReqList = FriendDAO.getInstance().sendFriendReqList(friendship);
		return sendFriendReqList;
	}

	@Override
	public ArrayList<BookMarkShip> recommendOutWeb(String userId) {
		ArrayList<BookMarkShip> outWebList = new ArrayList<BookMarkShip>();
		outWebList = FriendDAO.getInstance().recommendOutWeb(userId);
		return outWebList;
	}

	@Override
	public ArrayList<BookMarkShip> recommendInWeb(String userId) {
		ArrayList<BookMarkShip> inWebList = new ArrayList<BookMarkShip>();
		inWebList = FriendDAO.getInstance().recommendInWeb(userId);
		return inWebList;
	}
	
	@Override
	public void requestFriend(Friendship friendship) {
		FriendDAO.getInstance().requestFriend(friendship);
	}

	@Override
	public void deleteFriend(Friendship friendship) {
		FriendDAO.getInstance().deleteFriend(friendship);
	}
	
	public ArrayList<Message> outBox(Message message) {
		ArrayList<Message> outBoxList = null;
		outBoxList = MessageDAO.getInstance().outBox(message);
		return outBoxList;
	}

	@Override
	public ArrayList<Message> inBox(Message message) {
		ArrayList<Message> inBoxList = null;
		inBoxList = MessageDAO.getInstance().inBox(message);
		return inBoxList;
	}

	@Override
	public boolean sendMessage(Message message) {
		return MessageDAO.getInstance().sendMessage(message);
	}

	@Override
	public void accept(Friendship friendship) {
		FriendDAO.getInstance().accept(friendship);
	}

	@Override
	public void reject(Friendship friendship) {
		FriendDAO.getInstance().reject(friendship);
	}

	@Override
	public void cancel(Friendship friendship) {
		FriendDAO.getInstance().cancel(friendship);
	}
	
	@Override
	public void deleteMessage(String messageId) {
		MessageDAO.getInstance().deleteMessage(messageId);
	}

	@Override
	public Message getMessage(String messageId) {
		Message message = new Message();
		message = MessageDAO.getInstance().getMessage(messageId);
		return message;
	}


	@Override
	public void updateMessage(Message message) {
		MessageDAO.getInstance().updateMessage(message);
	}

	@Override
	public void recommend(BookMarkShip bookmarkship) {
		FriendDAO.getInstance().recommendSite(bookmarkship);
	}

	@Override
	public void recommendCancel(String bookMarkId) {
		FriendDAO.getInstance().recommendCancel(bookMarkId);
	}

	@Override
	public BookMark selectBookMark(int bookMarkId) {
		BookMark selectBookMarkList = null;

		selectBookMarkList = FriendDAO.getInstance().selectBookMark(bookMarkId);

		return selectBookMarkList;
	}

	@Override
	public Member getKey(String userId) {
		Member memberKey = FriendDAO.getInstance().getKey(userId);
		return memberKey;
	}

	@Override
	public boolean bookMarkExist(BookMarkShip bookmarkship) {
		boolean flag = FriendDAO.getInstance().bookMarkExist(bookmarkship);
		return flag;
	}

	@Override
	public ArrayList<Message> messageCount(Message message) {
		ArrayList<Message> msg = new ArrayList<Message>();
		msg = MessageDAO.getInstance().messageCount(message);
		return msg;
	}

	@Override
	public ArrayList<Member> requestFriendMe2Day(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}

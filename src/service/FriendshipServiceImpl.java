/*
 * 작성자: 신보미
 * 관리자: 신보미
 * */

package service;

import java.util.ArrayList;

import dao.FriendDAO;
import dto.BookMark;
import dto.BookMarkShip;
import dto.FriendStatus;
import dto.Friendship;
import dto.Member;
import dto.User;

public class FriendshipServiceImpl implements FriendshipServiceIF {

	@Override
	public ArrayList<Member> getFriendList(Friendship friend) {
		ArrayList<Member> friendList = null;
		friendList = FriendDAO.getInstance().getFriendList(friend);
		return friendList;
	}

	@Override
	public ArrayList<Member> getFriendListBar(Friendship friend) {
		ArrayList<Member> friendListBar = null;
		friendListBar = FriendDAO.getInstance().getFriendListBar(friend);
		return friendListBar;
	}

	@Override
	public Member me2Friend(String me2FriendId) {
		Member me2FriendInfo = null;
		me2FriendInfo = FriendDAO.getInstance().me2Friend(me2FriendId);
		return me2FriendInfo;
	}

	@Override
	public ArrayList<Member> searchFriend(User searchUser) {
		ArrayList<Member> searchList = null;
		searchList = FriendDAO.getInstance().getMemberById(searchUser);
		return searchList;
	}

	@Override
	/*
	 * public FriendStatus getFriendStatus(FriendStatus friendStatus) {
	 * FriendStatus getFriendStatus = null; getFriendStatus =
	 * FriendDAO.getInstance().getFriendStatus(friendStatus); return
	 * getFriendStatus; }
	 */
	public ArrayList<FriendStatus> getFriendStatus(FriendStatus friendStatus) {
		// FriendStatus getFriendStatus = null;
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
	public ArrayList<BookMarkShip> outWeb(String userId) {
		ArrayList<BookMarkShip> outWebList = new ArrayList<BookMarkShip>();
		outWebList = FriendDAO.getInstance().outWebList(userId);
		return outWebList;
	}

	@Override
	public ArrayList<BookMarkShip> inWeb(String userId) {
		ArrayList<BookMarkShip> inWebList = new ArrayList<BookMarkShip>();
		inWebList = FriendDAO.getInstance().inWebList(userId);
		return inWebList;
	}
	
	public ArrayList<String> getNameList(User searchUser) {
		return null;
		/*
		 * ArrayList<Member> memberList=null; ArrayList<String> searchList=new
		 * ArrayList<String>(); //try {
		 * memberList=FriendDAO.getInstance().getMemberById
		 * (searchUser.getKeyword());
		 * 
		 * for(Member member : memberList) searchList.add(member.getUserId());
		 * //} catch (RecordNotFoundException e) { //}
		 * 
		 * return searchList;
		 */
	}

	@Override
	public void requestFriend(Friendship friendship) {
		FriendDAO.getInstance().insertFriend(friendship);
	}

	@Override
	public ArrayList<Member> requestFriendMe2Day(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFriend(Friendship friendship) {
		FriendDAO.getInstance().deleteFriend(friendship);
	}

	/*@Override
	public ArrayList<Message> getOutBox(String userId) {
		ArrayList<Message> outBoxList = null;
		outBoxList = MessageDAO.getInstance().outBox(userId);
		return outBoxList;
	}*/

	/*@Override
	public ArrayList<Message> getInBox(String userId) {
		ArrayList<Message> inBoxList = null;
		inBoxList = MessageDAO.getInstance().inBox(userId);
		return inBoxList;
	}

	@Override
	public boolean sendMessage(Message message) {
		return MessageDAO.getInstance().sendMessage(message);
	}

	@Override
	public Message getMessageDatail(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	
*/
	@Override
	public ArrayList<String> getNameList(String keyword) {
		// TODO Auto-generated method stub
		return null;
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

	/*@Override
	public Message getMessage(String messageId) {
		Message message = new Message();
		message = MessageDAO.getInstance().getMessage(messageId);
		return message;
	}

	@Override
	public void deleteMessage(String messageId) {
		MessageDAO.getInstance().deleteMessage(messageId);
	}*/

	// 신보미가 시킨것
	@Override
	public ArrayList<Member> getMemberById(User searchUser) {
		ArrayList<Member> member = null;
		member = FriendDAO.getInstance().getMemberById(searchUser);
		return member;
	}

	/*@Override
	public void recommendSite(BookMarkShip bookmarkship) {
		FriendDAO.getInstance().recommendSite(bookmarkship);
	}*/

	@Override
	public void bookmarkCancel(String bookMarkId) {
		FriendDAO.getInstance().bookMarkCancel(bookMarkId);
	}

	/*@Override
	public void bookMarkRecommand(BookMarkShip bookmarkship) {
		FriendDAO.getInstance().bookMarkRecommand(bookmarkship);

	}
*/
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
	public void deleteMessage(String messageId) {
		// TODO Auto-generated method stub
		
	}

}

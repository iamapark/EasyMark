package service;

import java.util.ArrayList;

import dto.BookMark;
import dto.BookMarkShip;
import dto.FriendStatus;
import dto.Friendship;
import dto.Member;
import dto.Message;
import dto.User;

/**
 * 친구관리 서비스
 * @author 신보미
 */

public interface FriendshipServiceIF {
	
	/**
	 * 사용자가 아이디로 검색하여 결과 리스트를 불러오는 메소드
	 * @param Me2DayMember 미투데이 계정에 관한 정보가 담겨있는 변수
	 * @return ArrayList<Member> 검색 결과 데이터가 담겨 있다.
	 */
	public Member getKey(String userId);
	public ArrayList<Member> getFriendList(Friendship friend);
	public ArrayList<Member> getFriendListBar(Friendship friend);
	
	public ArrayList<Member> searchFriend(User searchUser);

	
	public ArrayList<FriendStatus> getFriendStatus(FriendStatus friendStatus);
	//public FriendStatus getFriendStatus(FriendStatus friendStatus);
	

	public ArrayList<String> getNameList(String keyword);
	/**
	 * 특정 사용자에게 친구 요청을 보낸다.
	 * @param String 친구 요청을 보내는 아이디
	 * @param String 친구 요처을 받는 아이디
	 * @return void
	 */
	public Member me2Friend(String me2FriendId);
	public void requestFriend(Friendship friendship);
	/**
	 * 사용자의 미투데이 계정에 친구로 등록되어 있는 리스트를 받아온 뒤, 
	 * 본 사이트의 미투데이 계정 가입자 리스트와 대조하여 일치하는 아이디가 있을 경우 
	 * 자동으로 친구 요청을 보낸다.
	 * @param String 친구 요청을 보내는 아이디
	 * @return ArrayList<Member> 사용자로부터 친구 요청을 받은 아이디 리스트
	 */
	public ArrayList<Member> requestFriendMe2Day(String userId);
	
	/**
	 * 특정 사용자를 친구 리스트에서 삭제한다
	 * @param String 사용자 아이디
	 * @param String 삭제 당하는 아이디
	 * @return void
	 */
	public void deleteFriend(Friendship friendship);

	/**
	 * 보낸 쪽지 리스트
	 * @param String 사용자 아이디
	 * @return ArrayList<Message> 쪽지 리스트
	 */
	public ArrayList<Member> takeFriendReqList(Friendship friendship);
	
	public ArrayList<Member> sendFriendReqList(Friendship friendship);
	
	public void deleteMessage(String messageId);
	public Message getMessage(String contents);
	
	public ArrayList<Message> getOutBox(String userId);

	/**
	 * 받은 쪽지 리스트
	 * @param String 사용자 아이디
	 * @return ArrayList<Message> 쪽지 리스트
	 */
	public ArrayList<Message> getInBox(String userId);

	/**
	 * 특정 사용자에게 쪽지를 보낸다.
	 * @param Message 보내는 쪽지에 대한 정보가 담겨 있는 변수
	 * @return int
	 */
	public boolean sendMessage(Message message);
	//public void sendMessage(Message message);
	/**
	 * 쪽지 상세 내용을 불러온다.
	 * @param int 쪽지 번호
	 * @return Message 쪽지 상세 정보가 담겨있다.
	 */
	/*쪽지 상세 내용을 불러온다.*/
	//public Message getMessageDatail(int num);
	
	/**
	 * 친구에게 사용자가 즐겨찾기한 웹 페이지 중 하나를 추천한다.
	 * @param String 사용자 아이디
	 * @param String 친구 아이디
	 * @param BookMark 웹 페이지 정보
	 * @return void
	 */
	

	/**
	 * 친구가 즐겨찾기한 웹 페이지 중 하나를 사용자 즐겨찾기 페이지로 가져온다.
	 * @param String 사용자 아이디
	 * @param BookMark 웹 페이지 정보
	 * @return void
	 */
	// 추천받은 웹페이지 목록 가져오기
	public ArrayList<BookMarkShip> inWeb(String userId);
	
	// 추천 한 웹페이지 목록 가져오기
	public ArrayList<BookMarkShip> outWeb(String userId);
	
	public void accept(Friendship friendship);
	public void reject(Friendship friendship);
	public void cancel(Friendship friendship);

	
	
	public void recommendSite(BookMarkShip bookmarkship);
	
	public void bookmarkCancel(String bookMarkId);
	
	//남기윤이 대충 만들고 있음( 신보미가 시킨 거)
	public ArrayList<Member> getMemberById(User searchUser);
	
	/*public void bookMarkRecommand(BookMarkShip bookmarkship);*/
	
	public BookMark selectBookMark(int bookMarkId);
	
	public boolean bookMarkExist(BookMarkShip bookmarkship);
	
}

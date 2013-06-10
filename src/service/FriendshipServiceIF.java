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
	 * 친구 목록 불러오기
	 * @param Friendship 사용자 아이디, 관계 설정된 정보
	 * @return ArrayList<Member> 친구 리스트
	 */
	public ArrayList<Member> getFriendList(Friendship friend);
	
	/**
	 * 사용자가 미투데이 계정으로 가입했을 경우 미투데이 키를 가져온다.
	 * @param String 사용자 아이디
	 * @return Member 사용자 정보
	 */
	public Member getKey(String userId);
	
	/**
	 * 아이디로 검색할 때, 현재 내 친구인 아이디와 친구가 아닌 아이디를 가려내기 위한 메소드
	 * @param FriendStatus 친구 상태 정보
	 * @return ArrayList<FriendStatus> 친구 상태 정보 리스트
	 */
	public ArrayList<FriendStatus> getFriendStatus(FriendStatus friendStatus);
	
	/**
	 * 내가 친구 요청 받은 상대방 리스트
	 * @param Friendship 친구 정보
	 * @return ArrayList<Member> 내가 친구 요청받은 상대방 리스트
	 */
	public ArrayList<Member> takeFriendReqList(Friendship friendship);
	
	/**
	 * 아이디로 검색할 때, 현재 내 친구인 아이디와 친구가 아닌 아이디를 가려내기 위한 메소드
	 * @param FriendStatus 친구 상태 정보
	 * @return ArrayList<FriendStatus> 친구 상태 정보 리스트
	 */
	public ArrayList<Member> sendFriendReqList(Friendship friendship);
	
	/**
	 * 사용자가 아이디로 검색하여 결과 리스트를 불러오는 메소드
	 * @param Me2DayMember 미투데이 계정에 관한 정보가 담겨있는 변수
	 * @return ArrayList<Member> 검색 결과 데이터가 담겨 있다.
	 */
	public ArrayList<Member> searchFriend(User searchUser);
	
	/**
	 * 나와 미투데이 친구이면서 EasyMark에 회원인 리스트를 불러온다.
	 * @param String 미투데이 계정으로 가입한 아이디
	 * @return Member 회원 정보
	 */
	public Member me2dayFriend(String me2FriendId);
	
	/**
	 * 친구 요청 메소드
	 * @param Friendship 친구 정보
	 */
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
	 * @param Friendship 사용자 아이디와 삭제 당하는 아이디를 가지고 있는 변수
	 * @return void
	 */
	public void deleteFriend(Friendship friendship);
	
	/**
	 * 쪽지 삭제 메소드
	 * @param String 쪽지 아이디
	 * @return ArrayList<Message> 쪽지 리스트
	 */
	public void deleteMessage(String messageId);
	
	/**
	 * 쪽지 상세 보기
	 * @param String 쪽지 아이디
	 * @return Message 쪽지 정보
	 */
	public Message getMessage(String messageId);
	
	/**
	 * 사용자가 쪽지를 읽을 경우, '읽음'으로 표시한다.
	 * @param Message 쪽지 정보
	 */
	public void updateMessage(Message message);
	
	/**
	 * 보낸 편지 목록
	 * @param Message 쪽지 정보
	 * @return ArrayList<Message> 쪽지 리스트
	 */
	public ArrayList<Message> outBox(Message message);
	
	/**
	 * 받은 쪽지 리스트
	 * @param String 사용자 아이디
	 * @return ArrayList<Message> 쪽지 리스트
	 */
	public ArrayList<Message> inBox(Message message);
	
	/**
	 * 쪽지를 보낸 후, 사용자가 보낸 쪽지 개수를 일거온다.
	 * @param Message 쪽지 정보
	 * @return ArrayList<Message> 쪽지 리스트
	 */
	public ArrayList<Message> messageCount(Message message);
	
	/**
	 * 쪽지 보내는 메소드
	 * @param Message 쪽지 정보
	 * @return boolean 쪽지 보내기에 성공하면 true, 실패하면 false
	 */
	public boolean sendMessage(Message message);
	
	/**
	 * 추천받은 웹 페이지 목록 가져오기
	 * @param String 사용자 아이디
	 * @return ArrayList<BookMarkShip> 웹 페이지 리스트
	 */
	public ArrayList<BookMarkShip> recommendInWeb(String userId);
	
	/**
	 * 추천 한 웹 페이지 목록 가져오기
	 * @param String 사용자 아이디
	 * @return ArrayList<BookMarkShip> 웹 페이지 리스트
	 */
	public ArrayList<BookMarkShip> recommendOutWeb(String userId);
	
	/**
	 * 친구가 추천한 웹 페이지를 자신의 북마크에 추가한다. 
	 * @param Friendship 친구 관계 정보
	 */
	public void accept(Friendship friendship);
	
	/**
	 * 친구가 추천한 웹 페이지를 거부한다. 
	 * @param Friendship 친구 관계 정보
	 */
	public void reject(Friendship friendship);
	
	/**
	 * 사용자가 보낸 친구 요청 취소 
	 * @param Friendship 친구 관계 정보
	 */
	public void cancel(Friendship friendship);

	/**
	 * 친구에게 웹 페이지 추천 
	 * @param BookMarkship 관련 정보가 담긴 변수
	 */
	public void recommend(BookMarkShip bookmarkship);
	
	/**
	 * 친구에게 추천했던 것을 삭제한다. 
	 * @param Friendship 친구 관계 정보
	 */
	public void recommendCancel(String bookMarkId);
	
	public BookMark selectBookMark(int bookMarkId);
	
	public boolean bookMarkExist(BookMarkShip bookmarkship);
	
}

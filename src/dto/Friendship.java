package dto;

public class Friendship {
	//private int friendshipId;
	private String userId;
	private String friendId;
	private String status;
	
	public Friendship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Friendship(String userId, String friendId, String status) {
		super();
		//this.friendshipId = friendshipId;
		this.userId = userId;
		this.friendId = friendId;
		this.status = status;
	}
	/*public int getFriendshipId() {
		return friendshipId;
	}
	public void setFriendshipId(int friendshipId) {
		this.friendshipId = friendshipId;
	}*/
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

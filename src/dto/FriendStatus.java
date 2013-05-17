package dto;

public class FriendStatus {
	private String userId;
	private String friendId;
	private String firstName;
	private String status;
	public FriendStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FriendStatus(String userId, String friendId, String firstName, String status) {
		super();
		this.userId = userId;
		this.friendId = friendId;
		this.firstName = firstName;
		this.status = status;
	}
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
}

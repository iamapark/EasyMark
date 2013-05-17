package dto;

import java.io.Serializable;

public class BookMarkShip implements Serializable {
	private static final long serialVersionUID = 1L;

	private int bookMarkId;
	private String bookMarkName;
	private String bookMarkUrl;
	private String bookMarkDescript;
	private String userId;
	private String friendId;
	private String status;

	public BookMarkShip() {
		super();
	}

	public BookMarkShip(int bookMarkId, String bookMarkName,
			String bookMarkUrl, String bookMarkDescript, String userId,
			String friendId, String status) {
		super();
		this.bookMarkId = bookMarkId;
		this.bookMarkName = bookMarkName;
		this.bookMarkUrl = bookMarkUrl;
		this.bookMarkDescript = bookMarkDescript;
		this.userId = userId;
		this.friendId = friendId;
		this.status = status;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookMarkUrl == null) ? 0 : bookMarkUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookMarkShip other = (BookMarkShip) obj;
		if (bookMarkUrl == null) {
			if (other.bookMarkUrl != null)
				return false;
		} else if (!bookMarkUrl.equals(other.bookMarkUrl))
			return false;
		return true;
	}
	

	public int getBookMarkId() {
		return bookMarkId;
	}

	public void setBookMarkId(int bookMarkId) {
		this.bookMarkId = bookMarkId;
	}

	public String getBookMarkName() {
		return bookMarkName;
	}

	public void setBookMarkName(String bookMarkName) {
		this.bookMarkName = bookMarkName;
	}

	public String getBookMarkUrl() {
		return bookMarkUrl;
	}

	public void setBookMarkUrl(String bookMarkUrl) {
		this.bookMarkUrl = bookMarkUrl;
	}

	public String getBookMarkDescript() {
		return bookMarkDescript;
	}

	public void setBookMarkDescript(String bookMarkDescript) {
		this.bookMarkDescript = bookMarkDescript;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookMarkShip [bookMarkId=" + bookMarkId + ", bookMarkName="
				+ bookMarkName + ", bookMarkUrl=" + bookMarkUrl
				+ ", bookMarkDescript=" + bookMarkDescript + ", userId="
				+ userId + ", friendId=" + friendId + ", status=" + status
				+ "]";
	}
	
	
}

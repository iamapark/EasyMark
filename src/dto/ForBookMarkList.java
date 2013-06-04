package dto;

public class ForBookMarkList {
	private String userId;
	private int parentId;
	public ForBookMarkList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ForBookMarkList(String userId, int parentId) {
		super();
		this.userId = userId;
		this.parentId = parentId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	 
}

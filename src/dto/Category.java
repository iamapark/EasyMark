package dto;

public class Category {
	private int categoryId;
	private String categoryName;
	private String userId;
	private int posX;
	private int posY;
	private String imgUrl;
	private int frequency;
	private String status;
	private int parentCategoryId;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int categoryId, String categoryName, String userId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.userId = userId;
	}
	

	public Category(int categoryId, String categoryName, String userId,
			int posX, int posY, int parentCategoryId) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.userId = userId;
		this.posX = posX;
		this.posY = posY;
		this.parentCategoryId = parentCategoryId;
	}
	
	public Category(String categoryName, String userId,
			int posX, int posY, String imgUrl, String status, int parentCategoryId) {
		super();
		this.categoryName = categoryName;
		this.userId = userId;
		this.posX = posX;
		this.posY = posY;
		this.imgUrl = imgUrl;
		this.status = status;
		this.parentCategoryId = parentCategoryId;
	}
	
	public Category(int categoryId, String categoryName, String userId, int parentCategoryId){
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.userId = userId;
		this.parentCategoryId = parentCategoryId;
	}
	
	public Category(int categoryId, String categoryName){
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	@Override
	public String toString(){
		return "categoryId: " + getCategoryId() + ", userId: " + getUserId();
	}
}

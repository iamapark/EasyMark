package dto;

public class Count {
	private String basis;
	private int countResult;
	
	public Count() {
		super();
	}

	public Count(String basis, int countResult) {
		super();
		this.basis = basis;
		this.countResult = countResult;
	}

	public String getBasis() {
		return basis;
	}

	public void setBasis(String basis) {
		this.basis = basis;
	}

	public int getCountResult() {
		return countResult;
	}

	public void setCountResult(int countResult) {
		this.countResult = countResult;
	}
}

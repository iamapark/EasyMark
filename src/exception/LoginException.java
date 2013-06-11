package exception;

public class LoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginException(){
		super();
	}
	
	public LoginException(String msg){
		super(msg);
	}
}

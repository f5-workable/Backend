package egovframework.job.exception;

public class LoginFailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LoginFailException() {
		super("로그인에 실패하였습니다.");
	}
}
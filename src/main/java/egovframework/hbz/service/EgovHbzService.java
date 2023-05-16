package egovframework.hbz.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface EgovHbzService {
	
	// 회원가입
	public void insertAction(HttpServletRequest request) throws Exception;
	
	// ID 중복확인
	public boolean idCheck(Map<String, Object> map) throws Exception;
}

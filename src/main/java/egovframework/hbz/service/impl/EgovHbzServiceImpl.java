package egovframework.hbz.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.hbz.dao.EgovHbzDao;
import egovframework.hbz.service.EgovHbzService;
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service
public class EgovHbzServiceImpl implements EgovHbzService{
	
	Logger logger = LoggerFactory.getLogger(EgovHbzServiceImpl.class);
	
	@Autowired
	EgovHbzDao egovHbzDao;
	
	// 아이디 중복확인
	public boolean idCheck(Map<String, Object> map) throws Exception{
		
		Map<String, Object> resultMap = egovHbzDao.idCheck(map);
		
		if(resultMap != null) {
			return false;
		}else {
			return true;
		}
		
	}
	
	// 회원가입
	public void insertAction(HttpServletRequest request) throws Exception{
		
		String id 		= request.getParameter("id");
		String password = request.getParameter("password");
		String name 	= request.getParameter("name");
		String tel		= request.getParameter("tel");
		String addr		= request.getParameter("addr");
		String email	= request.getParameter("email");
		
		// 비밀번호 암호화
		String enpassword = EgovFileScrty.encryptPassword(password, id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id"		, id);
		map.put("password"	, enpassword);
		map.put("name"		, name);
		map.put("tel"		, tel);
		map.put("addr"		, addr);
		map.put("email"		, email);
		
		egovHbzDao.insertAction(map);
	}
}
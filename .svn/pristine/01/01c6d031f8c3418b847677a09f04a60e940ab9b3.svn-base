package egovframework.hbz.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("EgovHbzDao")
public class EgovHbzDao extends EgovComAbstractDAO{
	
	Logger logger = LoggerFactory.getLogger(EgovHbzDao.class);
	
	public Map<String, Object> idCheck(Map<String, Object> map) throws Exception{
		
		Map<String, Object> resultMap = (Map<String, Object>) select("EgovHbzScpDao.selectIdCheck",map);
		
		System.out.println(resultMap);
		
		return resultMap;
	}
	
	// 회원가입
	public void insertAction(Map<String,Object> map) throws Exception {
		
		insert("loginDAO.insertAction", map);
    }
}

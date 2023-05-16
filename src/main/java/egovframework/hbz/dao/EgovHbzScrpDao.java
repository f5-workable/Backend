package egovframework.hbz.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository("EgovHbzScpDao")
public class EgovHbzScrpDao extends EgovComAbstractDAO{
	
	// 기상청 초단기실황 데이터 insert
	public void insertAction(Map<String,Object> map) throws Exception {
		
		insert("EgovHbzScpDao.insertAction", map);
    }
	
	// 기상청 초단기실황 데이터 전체조회
	public List<Map<String, Object>> selectAction(Map<String, Object> map) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list =  (List<Map<String, Object>>) list("EgovHbzScpDao.selectAction", map);
		
		return list;
	}
	
	public int getCnt() throws Exception{
		
		int cnt = (int) select("EgovHbzScpDao.getCnt");
		
		return cnt;
	}
	
}

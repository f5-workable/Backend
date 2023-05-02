package egovframework.hbz.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.hbz.service.impl.EgovHbzScrapServiceImpl;
import egovframework.hbz.service.impl.EgovHbzServiceImpl;

@Controller
public class EgovHbzController {
	
	@Autowired
	EgovHbzServiceImpl egovHbzServiceImpl;
	
	@Autowired
	EgovHbzScrapServiceImpl egovHbzScrapServiceImpl;
	
	Logger logger = LoggerFactory.getLogger(EgovHbzController.class);
	
	@ResponseBody
	@RequestMapping(value="/hbz/id_check.do", method=RequestMethod.POST)
	public Map<String, Object> idCheck(@RequestParam Map<String, Object> paramMap) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean yn = egovHbzServiceImpl.idCheck(paramMap);
		
		map.put("msg", yn);
		
		return map;
	}
	
	// 회원가입 페이지로 이동
	@RequestMapping(value="/hbz/insertPage.do", method=RequestMethod.GET)
	public String insertPage() throws Exception{
		
		return "cmm/uat/uia/EgovInsertUsr";
	}
	
	// 회원가입 실행
	@RequestMapping(value="/hbz/insertAction.do", method=RequestMethod.POST)
	public String insertAction(HttpServletRequest request) throws Exception{
		
		egovHbzServiceImpl.insertAction(request);
		
		return "forward:/cmm/main/mainPage.do";
	}
}

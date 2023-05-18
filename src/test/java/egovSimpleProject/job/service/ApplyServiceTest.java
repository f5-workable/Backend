package egovSimpleProject.job.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import egovframework.job.service.ApplyService;

class ApplyServiceTest {

	@Autowired
	private ApplyService applyService;
	
	   @BeforeAll
	    static void setup() {
	        System.out.println("@BeforeAll - executes once before all test methods in this class");
	    }
	   
	@Test
	void testGetApplyList() {
		fail("Not yet implemented");
	}

	@Test
	void testGetApplyById() {
		fail("Not yet implemented");
	}

	@Test
	void testAddApply() {
		fail("Not yet implemented");
	}

	@Test
	void testGetApplyListByMemberAndState() {
		long m_num = 2;
		String state = "지원완료";
		List<Object> applies= applyService.getApplyListByMemberAndState(m_num, state);
		for(Object apply : applies) {
			System.out.println(apply.toString());
		}
		
	}

	@Test
	void testCountApplyState() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteApply() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateApplyState() {
		fail("Not yet implemented");
	}

	@Test
	void testSelecteCrNumById() {
		fail("Not yet implemented");
	}

	@Test
	void testSelecteCRAndMemberById() {
		fail("Not yet implemented");
	}

}

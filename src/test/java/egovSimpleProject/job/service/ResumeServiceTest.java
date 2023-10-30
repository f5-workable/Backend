package egovSimpleProject.job.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import egovframework.job.dao.ResumeDAO;
import egovframework.job.service.ResumeService;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ResumeService.class)
class ResumeServiceTest {

	@Autowired
	private ResumeService service;
	
	@Autowired
	private ResumeDAO dao;
	
	@Test
	void testGetResumeById() {
		System.out.println(service.getResumeById(3L));
//		fail("Not yet implemented");
	}

}

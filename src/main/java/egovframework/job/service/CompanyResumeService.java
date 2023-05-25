package egovframework.job.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.CompanyResumeDAO;
import egovframework.job.dao.CompanyResumeRegionDAO;
import egovframework.job.dto.CompanyResumeDTO;
import egovframework.job.dto.CompanyResumeRegionDTO;
import egovframework.job.dto.ResumeDTO;
import egovframework.job.vo.CompanyResumeRegionVO;
import egovframework.job.vo.CompanyResumeVO;
import egovframework.job.vo.ResumeRegionVO;
import egovframework.job.vo.ResumeVO;

@Service
public class CompanyResumeService {

	@Autowired
	private CompanyResumeDAO companyResumeDAO;
	@Autowired
	private CompanyResumeRegionDAO companyResumeRegionDAO;
	
	// 등록 (이력서 -> 기업이력서)
	public long addCompanyResume(ResumeDTO resumeDto, List<ResumeRegionVO> regions) {
		CompanyResumeDTO insertDto = new CompanyResumeDTO();
		insertDto.toCompanyResume(resumeDto.toEntity());	//이력서 -> 기업이력서
		// 기업이력서 등록 후 아이디 반환
		companyResumeDAO.addCompanyResume(insertDto);
		// 기업 이력서 희망 지역 설정
		//addCrRegion(insertCrId,regions);
		for(ResumeRegionVO region: regions) {
			CompanyResumeRegionDTO crRegion = new CompanyResumeRegionDTO();
			crRegion.setCr_num(insertDto.getCr_num());// 기업 이력서 등록
			crRegion.setRegion(region.getRegion());	// 지역명 등록
			// insert
			companyResumeRegionDAO.insertCrRegion(crRegion);
		}
		return insertDto.getCr_num();
	}
	
	// 기업 이력서 희망 지역 설정
	public void addCrRegion(long insertCrId, List<ResumeRegionVO> regions) {
		for(ResumeRegionVO region: regions) {
			CompanyResumeRegionDTO crRegion = new CompanyResumeRegionDTO();
			crRegion.setCr_num(insertCrId);// 기업 이력서 등록
			crRegion.setRegion(region.getRegion());	// 지역명 등록
			// insert
			companyResumeRegionDAO.insertCrRegion(crRegion);
		}
	}
	
	// id에 따른 기업이력서 조회
	public CompanyResumeDTO seleteById(long cr_num) {
		// 기업 이력서 희망 지역 조회
		List<CompanyResumeRegionVO> crRegionList =companyResumeRegionDAO.selectCrResumeList(cr_num);
		// 아이디로 기업 이력서 조회
		CompanyResumeDTO dto = companyResumeDAO.selectById(cr_num);
		// 희망지역 추가
		dto.setRegion(crRegionList);
		return dto;
	}
	
	// id에 따른 기업이력서 삭제
	public int deleteById(long cr_num) {
		// 기업이력서 희망지역 삭제
		// companyResumeRegionDAO.deleteCrResume(cr_num);
		// 기업이력서 삭제
		return companyResumeDAO.deleteById(cr_num);
	}

}

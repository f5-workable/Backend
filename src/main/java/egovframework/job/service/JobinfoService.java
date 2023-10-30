package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.JobinfoDAO;
import egovframework.job.dao.WishDAO;
import egovframework.job.dto.JobinfoDTO;
import egovframework.job.dto.JobinfoSearchRequest;
import egovframework.job.dto.JobinfoSearchResponse;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishVO;

@Service
public class JobinfoService {

   @Autowired
   private JobinfoDAO dao;
   
// 구직정보 전체리스트 반환
   public List<JobinfoDTO> getJobinfoList() {
        return dao.selectJobinfoList();
    }
//  구직정보 JobinfoVO로 단일조회
    public JobinfoDTO getJById(Long id) {
    	return dao.selectJById(id);
    }
//  구직정보 생성
    @Transactional
    public JobinfoDTO addJobinfo(JobinfoDTO dto) {
       JobinfoDTO res = dao.addJobinfo(dto);
       return res;
    }
//  구직정보 수정
    @Transactional
    public void updateJobinfo(JobinfoDTO dto) {
       dao.updateJobinfo(dto);
    }
//  구직정보 삭제
    @Transactional
    public void deleteJobinfo(Long id) {
       dao.deleteJobinfo(id);
    }
//  구직정보 조건검색
    public List<JobinfoSearchResponse> searchJobinfo(JobinfoSearchRequest req) {
    	List<JobinfoSearchResponse> res = dao.searchJobinfo(req);
       return res;
    }
    // 기업별 업종(JOB_TYPE) 목록 조회
    public List<String> selectJobTypeByCNum(Long c_num){
    	return dao.selectJobTypeByCNum(c_num);
    }
}
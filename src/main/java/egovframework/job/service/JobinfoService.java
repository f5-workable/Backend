package egovframework.job.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.javassist.compiler.ast.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.job.dao.JobinfoDAO;
import egovframework.job.dao.WishDAO;
import egovframework.job.dto.JobinfoDTO;
import egovframework.job.vo.JobinfoResultVO;
import egovframework.job.vo.JobinfoSearchVO;
import egovframework.job.vo.JobinfoVO;
import egovframework.job.vo.WishVO;

@Service
public class JobinfoService {

   @Autowired
   private JobinfoDAO dao;
   
   @Autowired
   private WishDAO wishdao;
   
// 구직정보 전체리스트 반환
   public List<JobinfoVO> getJobinfoList() {
        return dao.selectJobinfoList();
    }
//  
    public JobinfoResultVO getJobinfoById(Long id) {
        return dao.selectJobinfoById(id);
    }
    
    public JobinfoVO getJById(Long id) {
    	return dao.selectJById(id);
    }
//  구직정보 생성
    @Transactional
    public JobinfoVO addJobinfo(JobinfoDTO dto) {
       JobinfoVO vo = dao.addJobinfo(dto);
       return vo;
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
    public List<JobinfoResultVO> searchJobinfo(JobinfoSearchVO vo) {
    	List<JobinfoResultVO> res = dao.searchJobinfo(vo);
       return res;
    }
    
    // 기업별 업종(JOB_TYPE) 목록 조회
    public List<String> selectJobTypeByCNum(long c_num){
    	return dao.selectJobTypeByCNum(c_num);
    }
    
}
package egovframework.job.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.job.dto.ApplyDTO;

import egovframework.job.vo.ApplyVO;

@Repository
public class ApplyDAO {


   @Autowired
   private SqlSession sqlSession;
   
   public List<ApplyVO> selectApplyList() {
      List<ApplyVO> companyVO = sqlSession.selectList("egovframework.mapper.job.ApplyMapper.selectApplyList");
      
      return companyVO;
   }

    public ApplyVO selectApplyById(Long id) {
       ApplyVO vo = sqlSession.selectOne("egovframework.mapper.job.ApplyMapper.selectApplyById", id);
        return vo;
    }

   //지원 등록
   public int createApply(ApplyDTO dto) {
      
       return sqlSession.insert("egovframework.mapper.job.ApplyMapper.createApply",dto );
      
   }
       
    
    //지원내역 상태별 조회
    
    //지원상태별 개수 조회
    
    //지원 취소       

}
package egovframework.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.job.dao.MemberDAO;
import egovframework.job.dto.MemberDTO;

@Service
public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public List<MemberDTO> getMemberList() {
        return memberDAO.selectMemberList();
    }

    public MemberDTO getMemberById(String id) {
        return memberDAO.selectMemberById(id);
    } 

    @Transactional
    public void addMember(MemberDTO memberDTO) {
        memberDAO.insertMember(memberDTO);
    }

    @Transactional
    public void updateMember(MemberDTO memberDTO) {
        memberDAO.updateMember(memberDTO);
    }

    @Transactional
    public void deleteMember(String id) {
        memberDAO.deleteMember(id);
    }
}

package egovframework.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import egovframework.job.dto.MemberDTO;

@Service
public class MemberDetailsService implements UserDetailsService{

	@Autowired
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		try {
			MemberDTO memberDTO = memberService.findById(id);
			
			if(memberDTO == null) {
				throw new UsernameNotFoundException("User not found with username: " + id);
			}
			
			MemberDetails memberDetails = new MemberDetails(memberDTO);
            memberDetails.setM_num(memberDTO.getM_num()); // m_num 값을 설정
            
            return memberDetails;
					
		} catch(Exception e) {
			throw new RuntimeException("Error occurred while loading user by username", e);
		}
	}
}

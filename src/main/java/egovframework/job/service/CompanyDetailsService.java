package egovframework.job.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import egovframework.job.dto.CompanyDTO;

@Service
public class CompanyDetailsService implements UserDetailsService{

	@Autowired
	private CompanyService companyService;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		
		try {
			CompanyDTO companyDTO = companyService.findById(id);
			
			if(companyDTO == null) {
				throw new UsernameNotFoundException("User not found with username: " + id);
			} 
			
			CompanyDetails companyDetails = new CompanyDetails(companyDTO);
			companyDetails.setC_num(companyDTO.getC_num());
			
			return companyDetails;
			
		} catch(Exception e) {
			throw new RuntimeException("Error occurred while loading user by username", e);
		}
	}
}

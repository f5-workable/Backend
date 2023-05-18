package egovframework.job.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import egovframework.job.dto.CompanyDTO;

public class CompanyDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CompanyDTO companyDTO;
	
	public CompanyDetails(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한 정보를 반환해야 합니다. 예를 들어, "ROLE_USER" 권한을 가진다고 가정하면:
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_COMPANY"));
    }
	
	@Override
    public String getPassword() {
        return companyDTO.getC_password();
    }

    @Override
    public String getUsername() {
        return companyDTO.getC_id();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았는지 여부를 반환해야 합니다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있지 않은지 여부를 반환해야 합니다.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명이 만료되지 않았는지 여부를 반환해야 합니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되어 있는지 여부를 반환해야 합니다.
        return true;
    }
}

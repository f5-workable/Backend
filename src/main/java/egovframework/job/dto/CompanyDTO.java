package egovframework.job.dto;

import lombok.Data;

import egovframework.job.domain.Company;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyDTO {
	private String c_name;
	private String c_id;
	private String password;
	private String rnum;
	private String address;
	private String logo;
	private String company_type;
	private String phone; 
	
	@Builder
    public CompanyDTO(final Company entity){
		this.c_name = entity.getC_name();
		this.c_id = entity.getC_id();
        this.password = entity.getPassword();
        this.phone = entity.getPhone();
        this.rnum = entity.getRnum();
        this.address = entity.getAddress();
        this.company_type = entity.getCompany_type();
        this.logo = entity.getLogo();
    }

    public Company toEntity(){
        return Company.builder()
        		.c_name(c_name)
        		.c_id(c_id)
        		.password(password)
        		.rnum(rnum)
        		.address(address)
        		.logo(logo)
        		.company_type(company_type)
        		.phone(phone)
                .build();
    }
}

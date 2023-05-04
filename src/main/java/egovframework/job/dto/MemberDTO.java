package egovframework.job.dto;

import lombok.Data;
import egovframework.job.domain.Member;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDTO {
	private long m_num;
	private String id;
	private String password;
	private String email;
	private String name;
	private String phone;
	private String profil;
	private boolean deleted;
	@Builder
    public MemberDTO(final Member entity){
		this.m_num = entity.getM_num();
		this.id = entity.getId();
		this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.phone = entity.getPhone();
        this.profil = entity.getProfil();
        this.deleted = entity.isDeleted();
    }

    public Member toEntity(){
        return Member.builder()
        		.m_num(m_num)
        		.id(id)
                .email(email)
                .name(name)
                .password(password)
                .phone(phone)
                .profil(profil)
                .deleted(deleted)
                .build();
    }


}

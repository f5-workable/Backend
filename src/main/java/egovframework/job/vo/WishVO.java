package egovframework.job.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WishVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long w_id;
	
	private Long m_num;
	private Long j_num;
	private boolean state;
}
package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// 이후에 삭제(WishVO로 그대로 받으면 됨) 
public class WishCreateVO {
	
	private Long user_id;
	private Long guzzik_id;
	private boolean state;
	
	@Builder
	public WishCreateVO(Long memberId, Long g_id, boolean state) {
		this.user_id = memberId;
		this.guzzik_id = g_id;
		this.state = state;
	}
}

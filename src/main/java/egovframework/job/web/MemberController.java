package egovframework.job.web;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.job.dto.MemberDTO;
import egovframework.job.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	EgovMessageSource egovMessageSource;

	@Autowired
	@Qualifier("memberPasswordEncoder")
	PasswordEncoder memberPasswordEncoder;

	// Constructor injection
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	// 회원가입 화면
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUpView(@ModelAttribute("MemberDTO") MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		return "/member/signup";
	}

	// 회원가입 처리
	@PostMapping("/signup")
    public ResponseEntity<String> memberSignUp(@RequestBody MemberDTO memberDTO) {
        
		try {
			// 입력받은 비밀번호를 암호화하여 저장
            String encodedPassword = memberPasswordEncoder.encode(memberDTO.getPassword());
            memberDTO.setPassword(encodedPassword);

            // 회원 정보 저장
            memberService.insertMember(memberDTO);
            return ResponseEntity.ok("회원가입이 성공적으로 처리되었습니다.");
        } catch (Exception e) {
        	String errorMessage = "회원가입 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

	// 로그아웃 처리
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 세션 무효화
			request.getSession().invalidate();

			// 로그아웃 성공 메시지 반환
			String successMessage = "로그아웃이 성공적으로 처리되었습니다.";
			return ResponseEntity.ok(successMessage);
		} catch (Exception e) {
			// 로그아웃 실패 메시지 반환
			String errorMessage = "로그아웃 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// 상세정보 화면
	@GetMapping("/info/{m_num}")
	public ResponseEntity<?> memberInfo(@PathVariable Long m_num) {
		try {
			// 멤버 정보 조회 로직
			MemberDTO memberDTO = memberService.findByMNum(m_num);
			return ResponseEntity.ok(memberDTO);
		} catch (Exception e) {
			String errorMessage = "서버에서 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}

	}

	// 상세정보 처리
	@PutMapping("/update/{m_num}")
	public ResponseEntity<?> updateMemberInfo(@PathVariable Long m_num, @RequestBody MemberDTO memberDTO) {
		try {
			// 멤버 정보 업데이트 로직
			memberDTO.setM_num(m_num);
			memberService.updateSequenceMemberDetail(memberDTO);
			return ResponseEntity.ok("멤버 정보가 업데이트되었습니다.");
		} catch (Exception e) {
			String errorMessage = "회원 정보 업데이트 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("errorMessage", errorMessage));
		}
	}

	// 비밀번호 찾기 페이지로 이동
	@GetMapping("/{id}/password")
	public ResponseEntity<String> getPasswordRecoveryPage(@PathVariable("id") String id) {
		try {
			// 회원 정보를 가져와서 페이지에 전달하거나 필요한 로직을 수행
			// ...

			// 예시: 회원 정보를 담은 객체를 JSON 형태로 반환
			MemberDTO memberDTO = memberService.getMemberDetail(id);
			if (memberDTO != null) {
				// 회원 정보가 존재하는 경우
				return ResponseEntity.ok(memberDTO.toString());
			} else {
				// 회원 정보가 없는 경우
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원 정보가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 비밀번호 찾기
	@PostMapping("/{id}/password")
	public ResponseEntity<String> findPassword(@PathVariable("id") String id, @RequestParam("name") String name,
			@RequestParam("phone") String phone) {
		try {
			String password = memberService.findPassword(id, name, phone);
			if (password != null) {
				return ResponseEntity.ok("회원 정보가 확인되었습니다. 새로운 비밀번호를 입력해주세요.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("잘못된 정보입니다. 다시 시도해주세요.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다. 다시 시도해주세요.");
		}
	}

	// 비밀번호 변경
	@PutMapping("/{id}/password")
	public ResponseEntity<String> changePassword(@PathVariable("id") String id,
			@RequestBody Map<String, String> request) {
		try {
			String password = request.get("password");
			MemberDTO memberDTO = memberService.findById(id);
			memberDTO.setPassword(password);
			memberService.updatePassword(memberDTO);
			return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 변경에 실패하였습니다.");
		}
	}

	// 회원 탈퇴
	@PostMapping("/delete/{id}")
	public ResponseEntity<String> deleteMember(@PathVariable String id) {
		try {

			memberService.deleteMember(id);

			String successMessage = "회원 ID: " + id + "님, 탈퇴가 성공적으로 처리되었습니다.";
			return ResponseEntity.ok(successMessage);
		} catch (Exception e) {
			String errorMessage = "회원 탈퇴 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}
}
package egovframework.job.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
	public ResponseEntity<?> actionSignUp(@RequestBody MemberDTO memberDTO) {
		
		try {
	        // 아이디 중복 체크
	        String id = memberDTO.getId();
	        if (memberService.isIdDuplicate(id)) {
	            return ResponseEntity.badRequest().body("아이디가 이미 사용 중입니다.");
	        }
	        
	        // 입력받은 비밀번호를 암호화하여 저장
	        String encodedPassword = memberPasswordEncoder.encode(memberDTO.getPassword());
	        memberDTO.setPassword(encodedPassword);

	        // 회원 정보 저장
	        memberService.registerMember(memberDTO);
	        
	        return ResponseEntity.ok(memberDTO); // 회원가입이 성공하면 회원 정보를 응답으로 반환
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

	// 로그인 후 홈 화면
	@GetMapping("/home")
	public ResponseEntity<String> homeView(Authentication authentication) {
		String username = authentication.getName();
		return ResponseEntity.ok("Welcome to the home page, " + username + "!");
	}

	// 상세정보 화면
	@GetMapping("/info/{id}")
	public ResponseEntity<?> memberInfo(@PathVariable String id, Authentication authentication) {
		try {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			MemberDTO memberDetail = memberService.getMemberDetail(id);

			if (memberDetail != null) {
				// Create a map to hold the member information
				Map<String, Object> response = new HashMap<>();
				response.put("id", userDetails.getUsername());
				response.put("member", memberDetail);

				return ResponseEntity.ok(response);
			} else {
				String errorMessage = "회원 정보를 찾을 수 없습니다.";
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
			}
		} catch (Exception e) {
			String errorMessage = "서버에서 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// 상세정보 수정 화면
	@GetMapping("/update/{id}")
	public ResponseEntity<?> memberUpdate(@PathVariable String id, Authentication authentication) {
		try {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			MemberDTO memberDetail = memberService.getMemberDetail(id);

			if (memberDetail != null) {
				// Create a map to hold the member information
				Map<String, Object> response = new HashMap<>();
				response.put("id", userDetails.getUsername());
				response.put("member", memberDetail);

				return ResponseEntity.ok(response);
			} else {
				String errorMessage = "회원 정보를 찾을 수 없습니다.";
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
			}
		} catch (Exception e) {
			String errorMessage = "서버에서 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// 상세정보 수정 처리
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateMemberDetail(@PathVariable String id, @RequestBody MemberDTO memberDTO) {
		try {
			memberDTO.setId(id);
			memberService.updateMemberDetail(memberDTO);
			String successMessage = "회원 정보가 성공적으로 업데이트되었습니다.";

			// Extract the desired fields from memberDTO
			Map<String, String> responseData = new HashMap<>();
			responseData.put("email", memberDTO.getEmail());
			responseData.put("phone", memberDTO.getPhone());
			responseData.put("profil", memberDTO.getProfil());

			// Create the response object with the responseData and successMessage
			Map<String, Object> response = new HashMap<>();
			response.put("data", responseData);
			response.put("successMessage", successMessage);

			// ResponseEntity에 response를 담아서 반환
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String errorMessage = "회원 정보 업데이트 중 오류가 발생했습니다.";

			// ResponseEntity에 errorMessage를 담아서 반환
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
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	// 비밀번호 찾기
	@PostMapping("/{id}/password")
    public ResponseEntity<String> findPassword(@PathVariable("id") String id,
                                               @RequestParam("name") String name,
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
                                                 @RequestParam("name") String name,
                                                 @RequestParam("phone") String phone,
                                                 @RequestBody String newPassword) {
		try {
            // 비밀번호 변경을 위한 인증 로직 추가
            String password = memberService.findPassword(id, name, phone);
            if (password != null) {
                // 인증 성공 시 비밀번호 변경
                // 암호화된 새로운 비밀번호로 업데이트
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                memberService.changePassword(id, name, phone, hashedPassword);
                return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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
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
import egovframework.job.dto.CompanyDTO;
import egovframework.job.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	EgovMessageSource egovMessageSource;

	@Autowired
	@Qualifier("companyPasswordEncoder")
	PasswordEncoder companyPasswordEncoder;

	// Constructor injection
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	// 회원가입 화면
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String sugnUpView(@ModelAttribute("CompanyDTO") CompanyDTO companyDTO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		return "/company/signup";
	}

	// 회원가입 처리
	@PostMapping("/signup")
	public ResponseEntity<String> companySignUp(@RequestBody CompanyDTO companyDTO) {

		try {
			// 입력받은 비밀번호를 암호화하여 저장
			String encodedPassword = companyPasswordEncoder.encode(companyDTO.getC_password());
			companyDTO.setC_password(encodedPassword);

			// 회원 정보 저장
			companyService.insertCompany(companyDTO);
			return ResponseEntity.ok("회원가입이 성공적으로 처리되었습니다.");
		} catch (Exception e) {
			String errorMessage = "회원가입 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// 아이디 중복 체크
	@GetMapping("/checkId/{c_id}")
	public ResponseEntity<String> checkDuplicateId(@PathVariable("c_id") String c_id) throws Exception {
		boolean isDuplicate = companyService.isIdDuplicate(c_id);
		String message = isDuplicate ? "이미 사용 중인 아이디입니다." : "사용 가능한 아이디입니다.";

		HttpStatus status = isDuplicate ? HttpStatus.CONFLICT : HttpStatus.OK;
		return ResponseEntity.status(status).body(message);
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
	@GetMapping("/info/{c_num}")
	public ResponseEntity<?> companyInfo(@PathVariable Long c_num) {
		try {
			// 멤버 정보 조회 로직
			CompanyDTO companyDTO = companyService.findByCNum(c_num);
			return ResponseEntity.ok(companyDTO);
		} catch (Exception e) {
			String errorMessage = "서버에서 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// 상세정보 처리
	@PutMapping("/update/{c_num}")
	public ResponseEntity<?> updateCompanyInfo(@PathVariable Long c_num, @RequestBody CompanyDTO companyDTO) {
		try {
			// 멤버 정보 업데이트 로직
			companyDTO.setC_num(c_num);
			companyService.updateSequenceCompanyDetail(companyDTO);
			return ResponseEntity.ok("멤버 정보가 업데이트되었습니다.");
		} catch (Exception e) {
			String errorMessage = "회원 정보 업데이트 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("errorMessage", errorMessage));
		}
	}

	// 비밀번호 찾기 페이지로 이동
	@GetMapping("/{c_id}/password")
	public ResponseEntity<String> getPasswordRecoveryPage(@PathVariable("c_id") String c_id) {
		try {
			CompanyDTO companyDTO = companyService.getCompanyDetail(c_id);
			if (companyDTO != null) {
				return ResponseEntity.ok(companyDTO.toString());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("기업 정보가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 비밀번호 찾기
	@PostMapping("/{c_id}/password")
	public ResponseEntity<String> findPassword(@PathVariable("c_id") String c_id, @RequestParam("c_name") String c_name,
			@RequestParam("phone") String phone) {
		try {
			String password = companyService.findPassword(c_id, c_name, phone);
			if (password != null) {
				return ResponseEntity.ok("기업 정보가 확인되었습니다. 새로운 비밀번호를 입력해주세요.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("잘못된 정보입니다. 다시 시도해주세요.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("오류가 발생했습니다. 다시 시도해주세요.");
		}
	}

	// 비밀번호 변경
	@PutMapping("/{c_id}/password")
	public ResponseEntity<String> changePassword(@PathVariable("c_id") String c_id,
			@RequestBody Map<String, String> request) {
		try {
			String c_password = request.get("c_password");
			CompanyDTO companyDTO = companyService.findById(c_id);
			companyDTO.setC_password(c_password);
			companyService.updatePassword(companyDTO);
			return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("비밀번호 변경에 실패하였습니다.");
		}
	}

	// 기업 탈퇴
	@PostMapping("/delete/{c_id}")
	public ResponseEntity<String> deleteMember(@PathVariable String c_id) {
		try {

			companyService.deleteCompany(c_id);

			String successMessage = "기업 ID: " + c_id + "님, 탈퇴가 성공적으로 처리되었습니다.";
			return ResponseEntity.ok(successMessage);
		} catch (Exception e) {
			String errorMessage = "회원 탈퇴 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}
}

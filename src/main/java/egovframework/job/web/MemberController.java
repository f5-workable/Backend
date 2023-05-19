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
	@RequestMapping(value = "/signup.do", method = RequestMethod.GET)
	public String signUpView(@ModelAttribute("MemberDTO") MemberDTO memberDTO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		return "/member/signup";
	}

	/*
	 * // 회원가입 처리
	 * 
	 * @RequestMapping(value = "/signup.do", method = RequestMethod.POST) public
	 * String actionSignUp(@ModelAttribute("MemberDTO") MemberDTO memberDTO,
	 * ModelMap model) {
	 * 
	 * // 입력받은 비밀번호를 암호화하여 저장 String encodedPassword =
	 * memberPasswordEncoder.encode(memberDTO.getPassword());
	 * memberDTO.setPassword(encodedPassword);
	 * 
	 * try { // 회원 정보 저장 memberService.registerMember(memberDTO);
	 * model.addAttribute("successMessage", "회원가입이 완료되었습니다."); } catch (Exception e)
	 * { model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다."); }
	 * 
	 * return "/member/login"; }
	 */

	// 회원가입 처리
	@PostMapping("/signup.do")
	public ResponseEntity<?> actionSignUp(@RequestBody MemberDTO memberDTO) {
		// 입력받은 비밀번호를 암호화하여 저장
		String encodedPassword = memberPasswordEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(encodedPassword);

		try {
			// 회원 정보 저장
			memberService.registerMember(memberDTO);
			return ResponseEntity.ok(memberDTO); // 회원가입이 성공하면 회원 정보를 응답으로 반환
		} catch (Exception e) {
			String errorMessage = "회원가입 중 오류가 발생했습니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// 로그인 화면
	// @RequestMapping(value = "/login.do", method = RequestMethod.GET)
	// public String loginView(@ModelAttribute("MemberDTO") MemberDTO memberDTO,
	// HttpServletRequest request,
	// HttpServletResponse response, ModelMap model) throws Exception {
	// return "/member/login";
	// }

	/*
	 * // 로그인 처리
	 * 
	 * @RequestMapping(value = "/login.do", method = RequestMethod.POST) public
	 * String actionLigin(@ModelAttribute("MemberDTO") MemberDTO memberDTO,
	 * HttpServletRequest request, ModelMap model) throws Exception { MemberDTO
	 * resultDTO = memberService.actionLogin(memberDTO); boolean loginPolicyYn =
	 * true;
	 * 
	 * if (resultDTO != null && resultDTO.getId() != null &&
	 * !resultDTO.getId().equals("") && loginPolicyYn) {
	 * request.getSession().setAttribute("MemberDTO", resultDTO); return
	 * "foward:/member/home"; } else { model.addAttribute("message",
	 * egovMessageSource.getMessage("fail.common.login")); return "/member/login"; }
	 * }
	 */

	// 로그인 처리
	@PostMapping("/login.do")
	public ResponseEntity<String> actionLogin(@RequestBody MemberDTO memberDTO, HttpServletRequest request) throws Exception {
		MemberDTO resultDTO = memberService.actionLogin(memberDTO);
		boolean loginPolicyYn = true;

		if (resultDTO != null && resultDTO.getId() != null && !resultDTO.getId().equals("") && loginPolicyYn) {
			request.getSession().setAttribute("MemberDTO", resultDTO);
			return ResponseEntity.ok("로그인이 완료되었습니다.");
		} else {
			String errorMessage = "아이디나 패스워드가 틀립니다.";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}

	// 로그아웃 처리
	@GetMapping("/logout.do")
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

	/*
	 * // 로그인 후 홈 화면
	 * 
	 * @RequestMapping(value = "/home.do", method = RequestMethod.GET) public String
	 * homeView(@ModelAttribute("MemberDTO") MemberDTO memberDTO, Model model,
	 * Authentication authentication) { String username = authentication.getName();
	 * model.addAttribute("id", username); return "/member/home"; }
	 */

	// 로그인 후 홈 화면
	@GetMapping("/home.do")
	public ResponseEntity<String> homeView(Authentication authentication) {
		String username = authentication.getName();
		return ResponseEntity.ok("Welcome to the home page, " + username + "!");
	}

	/*
	 * // 상세정보 화면
	 * 
	 * @RequestMapping(value = "/info.do/{id}", method = RequestMethod.GET) public
	 * String memberInfo(@PathVariable String id, Authentication authentication,
	 * Model model) {
	 * 
	 * // 현재 로그인한 사용자의 정보를 얻어온다. try { UserDetails userDetails = (UserDetails)
	 * authentication.getPrincipal(); MemberDTO memberDetail =
	 * memberService.getMemberDetail(id);
	 * 
	 * model.addAttribute("id", userDetails.getUsername());
	 * model.addAttribute("member", memberDetail); return "/member/info"; } catch
	 * (Exception e) { // Handle the exception or show an error page return "error";
	 * } }
	 */

	// 상세정보 화면
	@GetMapping("/info.do/{id}")
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

	/*
	 * // 상세정보 수정 화면
	 * 
	 * @RequestMapping(value = "/update.do/{id}", method = RequestMethod.GET) public
	 * String memberUpdate(@PathVariable String id, Authentication authentication,
	 * Model model) {
	 * 
	 * // 현재 로그인한 사용자의 정보를 얻어온다. try { UserDetails userDetails = (UserDetails)
	 * authentication.getPrincipal(); MemberDTO memberDetail =
	 * memberService.getMemberDetail(id);
	 * 
	 * model.addAttribute("id", userDetails.getUsername());
	 * model.addAttribute("member", memberDetail); return "/member/update"; } catch
	 * (Exception e) { // Handle the exception or show an error page return "error";
	 * } }
	 */

	// 상세정보 수정 화면
	@GetMapping("/update.do/{id}")
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

	
	/*
	 * // 상세정보 수정 처리
	 * 
	 * @RequestMapping(value = "/update.do/{id}", method = RequestMethod.POST)
	 * public String updateMemberDetail(@PathVariable String
	 * id, @ModelAttribute("member") MemberDTO memberDTO) { try {
	 * memberDTO.setId(id); memberService.updateMemberDetail(memberDTO); return
	 * "redirect:/member/info.do/" + id; } catch (Exception e) { // Handle the
	 * exception or show an error page return "error"; } }
	 */

	@PutMapping("/update.do/{id}") 
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
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("errorMessage", errorMessage));
	    }
	}

	/*
	 * // 회원 탈퇴
	 * 
	 * @RequestMapping(value = "/delete.do", method = RequestMethod.GET) public
	 * String deleteMember(Authentication authentication) { try { UserDetails
	 * userDetails = (UserDetails) authentication.getPrincipal(); String id =
	 * userDetails.getUsername();
	 * 
	 * memberService.deleteMember(id);
	 * 
	 * return "redirect:/member/login.do"; } catch (Exception e) { // Handle the
	 * exception or show an error page return "error"; } }
	 */

	// 회원 탈퇴
	@PostMapping("/delete.do/{id}")
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
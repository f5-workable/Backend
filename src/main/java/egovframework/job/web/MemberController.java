package egovframework.job.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.job.dto.MemberDTO;
import egovframework.job.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	EgovMessageSource egovMessageSource;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	// Constructor injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
	
	
	// 회원가입 화면
	@RequestMapping(value = "/signup.do", method = RequestMethod.GET)
	public String signUpView(@ModelAttribute("MemberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "/member/signup";
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String actionSignUp(@ModelAttribute("MemberDTO") MemberDTO memberDTO, ModelMap model) {
		
		// 입력받은 비밀번호를 암호화하여 저장
	    String encodedPassword = passwordEncoder.encode(memberDTO.getPassword());
	    memberDTO.setPassword(encodedPassword);

	    try {
	        // 회원 정보 저장
	        memberService.registerMember(memberDTO);
	        model.addAttribute("successMessage", "회원가입이 완료되었습니다.");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
	    }

	    return "/member/login";
	}
	
	// 로그인 화면
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("MemberDTO") MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "/member/login";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String actionLigin(@ModelAttribute("MemberDTO") MemberDTO memberDTO, HttpServletRequest request, ModelMap model) throws Exception {
		MemberDTO resultDTO = memberService.actionLogin(memberDTO);
		boolean loginPolicyYn = true;
		
		if(resultDTO != null && resultDTO.getId() != null && !resultDTO.getId().equals("") && loginPolicyYn) {
			request.getSession().setAttribute("MemberDTO", resultDTO);
			return "foward:/member/home";
		} else {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "/member/login";
		}
	}
	
	// 로그인 후 홈 화면
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String homeView(@ModelAttribute("MemberDTO") MemberDTO memberDTO, Model model, Authentication authentication) {
		String username = authentication.getName();
	    model.addAttribute("id", username);
		return "/member/home";
	}
	
	// 상세정보 화면
	@RequestMapping(value = "/info.do/{id}", method = RequestMethod.GET)
	public String memberInfo(@PathVariable String id, Authentication authentication, Model model) {
		
		// 현재 로그인한 사용자의 정보를 얻어온다.
        try {
        	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDetail = memberService.getMemberDetail(id);
            
            model.addAttribute("id", userDetails.getUsername());
            model.addAttribute("member", memberDetail);
            return "/member/info";
        } catch (Exception e) {
            // Handle the exception or show an error page
            return "error";
        }
	}
	
	// 상세정보 수정 화면
	@RequestMapping(value = "/update.do/{id}", method = RequestMethod.GET)
	public String memberUpdate(@PathVariable String id, Authentication authentication, Model model) {
		
		// 현재 로그인한 사용자의 정보를 얻어온다.
		try {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDetail = memberService.getMemberDetail(id);
            
            model.addAttribute("id", userDetails.getUsername());
            model.addAttribute("member", memberDetail);
            return "/member/update";
        } catch (Exception e) {
            // Handle the exception or show an error page
            return "error";
        }
	}
	
	// 상세정보 수정 처리
	@RequestMapping(value = "/update.do/{id}", method = RequestMethod.POST)
    public String updateMemberDetail(@PathVariable String id, @ModelAttribute("member") MemberDTO memberDTO) {
        try {
            memberDTO.setId(id);
            memberService.updateMemberDetail(memberDTO);
            return "redirect:/member/info.do/" + id;
        } catch (Exception e) {
            // Handle the exception or show an error page
            return "error";
        }
    }
	
	// 회원 탈퇴
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String deleteMember(Authentication authentication) {
		try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String id = userDetails.getUsername();
            
            memberService.deleteMember(id);
            
            return "redirect:/member/login.do";
        } catch (Exception e) {
            // Handle the exception or show an error page
            return "error";
        }
	}
}
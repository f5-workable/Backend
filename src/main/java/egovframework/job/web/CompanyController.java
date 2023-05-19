package egovframework.job.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import egovframework.job.dto.CompanyDTO;
import egovframework.job.service.CompanyService;

@Controller
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
	@RequestMapping(value = "signup.do", method = RequestMethod.GET)
	public String sugnUpView(@ModelAttribute("CompanyDTO") CompanyDTO companyDTO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		return "/company/signup";
	}

	// 회원가입 처리
	
	@RequestMapping(value = "/signup.do", method = RequestMethod.POST)
	public String actionSignUp(@ModelAttribute("CompanyDTO") CompanyDTO companyDTO, ModelMap model) {

		// 입력받은 비밀번호를 암호화하여 저장
		String encodedPassword = companyPasswordEncoder.encode(companyDTO.getC_password());
		companyDTO.setC_password(encodedPassword);

		try {
			// 회원 정보 저장
			companyService.registerCompany(companyDTO);
			model.addAttribute("successMessage", "회원가입이 완료되었습니다.");
		} catch (Exception e) {
			model.addAttribute("errorMessage", "회원가입 중 오류가 발생했습니다.");
		}

		return "/company/login";
	}

	// 로그인 화면
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginView(@ModelAttribute("CompanyDTO") CompanyDTO companyDTO, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		return "/company/login";
	}

	// 로그인 처리
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String actionLogin(@ModelAttribute("CompanyDTO") CompanyDTO companyDTO, HttpServletRequest request,
			ModelMap model) throws Exception {
		CompanyDTO resultDTO = companyService.actionLogin(companyDTO);
		boolean loginPolicyYn = true;

		if (resultDTO != null && resultDTO.getC_id() != null && !resultDTO.getC_id().equals("") && loginPolicyYn) {
			request.getSession().setAttribute("CompanyDTO", resultDTO);
			return "foward:/company/home";
		} else {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "/company/login";
		}
	}

	// 로그인 후 홈 화면
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String homeView(@ModelAttribute("CompanyDTO") CompanyDTO companyDTO, Model model,
			Authentication authentication) {
		String username = authentication.getName();
		model.addAttribute("id", username);
		return "/company/home";
	}

	// 상세정보 화면
	@RequestMapping(value = "/info.do/{id}", method = RequestMethod.GET)
	public String companyInfo(@PathVariable String id, Authentication authentication, Model model) {

		// 현재 로그인한 사용자의 정보를 얻어온다.
		try {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			CompanyDTO companyDetail = companyService.getCompanyDetail(id);

			model.addAttribute("id", userDetails.getUsername());
			model.addAttribute("company", companyDetail);
			return "/company/info";
		} catch (Exception e) {
			// Handle the exception or show an error page
			return "error";
		}
	}

	// 상세정보 수정 화면
	@RequestMapping(value = "/update.do/{id}", method = RequestMethod.GET)
	public String companyUpdate(@PathVariable String id, Authentication authentication, Model model) {

		// 현재 로그인한 사용자의 정보를 얻어온다.
		try {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			CompanyDTO companyDetail = companyService.getCompanyDetail(id);

			model.addAttribute("id", userDetails.getUsername());
			model.addAttribute("company", companyDetail);
			return "/company/update";
		} catch (Exception e) {
			// Handle the exception or show an error page
			return "error";
		}
	}

	// 상세정보 수정 처리
	@RequestMapping(value = "/update.do/{id}", method = RequestMethod.POST)
	public String updateCompanyDetail(@PathVariable String id, @ModelAttribute("company") CompanyDTO companyDTO) {
		try {
			companyDTO.setC_id(id);
			companyService.updateCompanyDetail(companyDTO);
			return "redirect:/company/info.do/" + id;
		} catch (Exception e) {
			// Handle the exception or show an error page
			return "error";
		}
	}

	// 기업 탈퇴
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public String deleteCompany(Authentication authentication) {
		try {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String id = userDetails.getUsername();

			companyService.deleteCompany(id);

			return "redirect:/company/login.do";
		} catch (Exception e) {
			// Handle the exception or show an error page
			return "error";
		}
	}
}

package egovframework.job.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        
        // 로그인 실패 후 JSON 응답 생성
        String jsonResponse = "{ \"message\": \"로그인에 실패하였습니다.\" }";
        
        // JSON 형식으로 응답을 설정
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        // JSON 응답을 출력
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}

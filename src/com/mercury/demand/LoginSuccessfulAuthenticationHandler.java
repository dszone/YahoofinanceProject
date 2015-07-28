package com.mercury.demand;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.mercury.beans.User;
import com.mercury.services.UserService;

public class LoginSuccessfulAuthenticationHandler extends SavedRequestAwareAuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
		String userTargetUrl = "/main.html";
		String adminTargetUrl = "/admin.html";
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(roles.contains("ROLE_ADMIN")){
			getRedirectStrategy().sendRedirect(request, response, adminTargetUrl);
		} else if(roles.contains("ROLE_USER")){
			getRedirectStrategy().sendRedirect(request, response, userTargetUrl);
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}
   }
}

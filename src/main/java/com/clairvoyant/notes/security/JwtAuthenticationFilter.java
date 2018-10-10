package com.clairvoyant.notes.security;

import com.clairvoyant.notes.service.impl.CustomNotesUserDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Value("${app.jwt.header}")
	private String tokenRequestHeader;

	@Value("${app.jwt.header.prefix}")
	private String tokenRequestHeaderPrefix;

	private static final Logger log = Logger.getLogger(JwtAuthenticationFilter.class);

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private CustomNotesUserDetailsService customUserDetailsService;

	/**
	 * Filter the incoming request for a valid token in the request header
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
		// String username;
		String authToken = getToken(request);
		UsernamePasswordAuthenticationToken jwtUser = null;
		if (authToken != null) {
			try {
				jwtUser = jwtTokenProvider.getUser(authToken);
				SecurityContextHolder.getContext().setAuthentication(jwtUser);
			} catch (Exception ex) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
			}

		}
		filterChain.doFilter(request, response);
	}

	/**
	 * Extract the token from the Authorization request header
	 */
	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(tokenRequestHeader);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(tokenRequestHeaderPrefix)) {
			log.info("Extracted Token: " + bearerToken);
			return bearerToken.replace(tokenRequestHeaderPrefix, "");
		}
		return null;
	}
	private String getToken(HttpServletRequest request) {

		String authHeader = request.getHeader(tokenRequestHeader);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
}

package com.clairvoyant.notes.resource;

import com.clairvoyant.notes.model.CustomNotesUserDetails;
import com.clairvoyant.notes.model.payload.LoginRequest;
import com.clairvoyant.notes.model.token.JwtAuthenticationToken;
import com.clairvoyant.notes.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${app.jwt.header.prefix}")
	private String tokenRequestHeaderPrefix;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody LoginRequest  loginRequest, HttpServletResponse response)
					throws AuthenticationException, IOException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authentication);


			CustomNotesUserDetails customNotesUserDetails = (CustomNotesUserDetails) authentication.getPrincipal();
			String token = jwtTokenProvider.generateToken(customNotesUserDetails);

			// Return the token
			response.addHeader("Authorization", "Bearer " + token);
			logger.debug("Login Successful");
			return ResponseEntity.ok(new JwtAuthenticationToken(token,tokenRequestHeaderPrefix,jwtTokenProvider.getExpiryDuration()));
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

}

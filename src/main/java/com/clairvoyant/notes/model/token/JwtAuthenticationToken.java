package com.clairvoyant.notes.model.token;

public class JwtAuthenticationToken  {

	private String token;
	private String tokenType;
	private Long expiryDuration;

	public JwtAuthenticationToken() {

	}

	public JwtAuthenticationToken(String token,String tokenType, Long expiryDuration) {
		this.token = token;
		this.tokenType = tokenType;
		this.expiryDuration = expiryDuration;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Long getExpiryDuration() {
		return expiryDuration;
	}

	public void setExpiryDuration(Long expiryDuration) {
		this.expiryDuration = expiryDuration;
	}
}

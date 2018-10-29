package com.clairvoyant.notes.Exception.Model;

public class NotesResponse {

	private String message;

	public NotesResponse() {

	}

	public NotesResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

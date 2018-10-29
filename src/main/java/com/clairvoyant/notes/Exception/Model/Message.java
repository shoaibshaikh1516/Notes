package com.clairvoyant.notes.Exception.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Message {
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "IST")
	private Date date;
	private String Description;

	public Message() {

	}

	public Message(String message, Date date, String description) {
		super();
		this.message = message;
		this.date = date;
		Description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}

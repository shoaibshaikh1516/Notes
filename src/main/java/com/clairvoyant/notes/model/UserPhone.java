package com.clairvoyant.notes.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the tblUserPhone database table.
 * 
 */
@Entity
@Table(name = "tblUserPhone", schema = "dbo")
public class UserPhone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userphoneid;

	private boolean isprimary;

	private String phonenumber;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	public UserPhone() {
	}

	public int getUserphoneid() {
		return this.userphoneid;
	}

	public void setUserphoneid(int userphoneid) {
		this.userphoneid = userphoneid;
	}

	public boolean getIsprimary() {
		return this.isprimary;
	}

	public void setIsprimary(boolean isprimary) {
		this.isprimary = isprimary;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}
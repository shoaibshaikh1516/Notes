package com.clairvoyant.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tblUser", schema = "dbo")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;

	private String emailaddress;

	private String firstname;

	private boolean isactive;

	private String lastname;

	private String password;

	@ManyToOne
	@JoinColumn(name = "userroleid")
	private UserRole userrole;

	public User() {
	}

	public User(String emailaddress, String password) {
		this.emailaddress = emailaddress;
		this.password = password;

	}

	public User(String emailaddress, String firstname, String lastname) {
		super();
		this.emailaddress = emailaddress;
		this.firstname = firstname;
		this.lastname = lastname;

	}

	public User(User user) {

		this.emailaddress = user.getEmailaddress();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.isactive=user.isIsactive();
		this.userrole=user.getUserrole();
		this.userid=user.getUserid();
		this.password=user.getPassword();

	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserrole() {
		return userrole;
	}

	public void setUserrole(UserRole userrole) {
		this.userrole = userrole;
	}
}
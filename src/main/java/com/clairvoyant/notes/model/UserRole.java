package com.clairvoyant.notes.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblUserRole", schema = "dbo")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userroleid;

	private String userrole;

	public UserRole() {
	}

	public int getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(int userroleid) {
		this.userroleid = userroleid;
	}

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

}
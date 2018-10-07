package com.clairvoyant.notes.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tblCountry", schema = "dbo")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int countryid;

	private String iso2;

	private String iso3;

	private String countryname;

	public Country() {
	}

	public int getCountryid() {
		return this.countryid;
	}

	public void setCountryid(int countryid) {
		this.countryid = countryid;
	}

	public String getIso2() {
		return this.iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public String getIso3() {
		return this.iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}


}
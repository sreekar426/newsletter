package com.sreekar.model;

public class UtilityDto {

	public UtilityDto(String name, String url) {
		this.name = name;
		this.url = url;
	}

	String email;
	String name;
	String url;
	String selector;
	String unselector;

	public String getUnselector() {
		return unselector;
	}

	public void setUnselector(String unselector) {
		this.unselector = unselector;
	}

	public String getSelector() {
		return selector;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}

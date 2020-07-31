package com.model;

public class Request {
	private int id;

	private String name;

	private String username;

	private String email;

	private Address address;

	private String phone;

	private String website;

	private Company company;
	private String title;

	private String body;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Company getCompany() {
		return this.company;
	}
}

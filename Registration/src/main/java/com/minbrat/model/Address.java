package com.minbrat.model;

public class Address {
private int id;
private int registrationId;
private String addHno;
private String street;
private String city;
private String country;
private String pincode;
private String state;



public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRegistrationId() {
	return registrationId;
}
public void setRegistrationId(int registrationId) {
	this.registrationId = registrationId;
}
public String getAddHno() {
	return addHno;
}
public void setAddHno(String addHno) {
	this.addHno = addHno;
}
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}


}

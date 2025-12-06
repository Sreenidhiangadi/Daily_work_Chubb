package com.example.demo.request;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
private String village;
private String state;
private String pincode;
public String getVillage() {
	return village;
}
public void setVillage(String village) {
	this.village = village;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}

}

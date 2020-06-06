package com.igate.moviebooking.dto;

public class UserDetails {
	
	int userId;
	String uName;
	String uPassword;
	String gender;
	String phone;
	String emailid;
	String address;
	int age;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", uName=" + uName + ", uPassword=" + uPassword + ", gender=" + gender
				+ ", phone=" + phone + ", emailid=" + emailid + ", address=" + address + ", age=" + age + "]";
	}
	
}

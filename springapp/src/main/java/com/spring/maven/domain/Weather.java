package com.spring.maven.domain;

public class Weather {
private String state;
private String city;
private String temp;
private String pinCode;
private boolean weatherFind;
private String msg;

public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public boolean isWeatherFind() {
	return weatherFind;
}
public void setWeatherFind(boolean weatherFind) {
	this.weatherFind = weatherFind;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getTemp() {
	return temp;
}
public void setTemp(String temp) {
	this.temp = temp;
}
public String getPinCode() {
	return pinCode;
}
public void setPinCode(String pinCode) {
	this.pinCode = pinCode;
}


}

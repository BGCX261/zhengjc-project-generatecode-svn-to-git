package cn.com.photop.sap.contactme.model;

import java.util.Date;


public class WeatherInfo extends BaseModel {
	
	private String city;
	private String cityid;
	private String temp1;
	private String temp2;
	private String weather;
	private String img1Url;
	
	private String img2Url;
	private String img1;
	private String img2;
	private String nowDate;
	
	public String getNowDate() {
		return nowDate;
	}
	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityid() {
		return cityid;
	}
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	public String getTemp1() {
		return temp1;
	}
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getTemp2() {
		return temp2;
	}
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg1Url() {
		return img1Url;
	}
	public void setImg1Url(String img1Url) {
		this.img1Url = img1Url;
	}
	public String getImg2Url() {
		return img2Url;
	}
	public void setImg2Url(String img2Url) {
		this.img2Url = img2Url;
	}
}


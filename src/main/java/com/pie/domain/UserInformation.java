package com.pie.domain;  

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pie.commons.IdEntity;

/**
 * User 用户详情信息实体
 * @author bruce_000
 */
@Entity
@Table(name = "user_information")
public class UserInformation extends IdEntity{
	private static final long serialVersionUID = 1L;
	private String nickName;
	private String sex;
	private Date birthdate;
	private String telephone;
	private String address;
	private String hobbies;
	private User user ;
	
	@Column(name = "nickName")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name = "birthdate")
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	@Column(name = "telephone")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "hobbies")
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	@OneToOne(optional=false,cascade={CascadeType.ALL})  
	@JoinColumn(name="user_id")  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
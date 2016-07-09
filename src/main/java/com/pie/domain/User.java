package com.pie.domain;  

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pie.commons.IdEntity;

/**
 * User 用户实体
 * @author bruce_000
 */
@Entity
@Table(name = "user")
public class User extends IdEntity{
	private static final long serialVersionUID = 1L;
	private String name;  
    private String password;  
    private BigDecimal money = new BigDecimal(0);
    private Date time = new Date();
    private UserInformation userInformation;
    // 一个用户对应多个角色
    private List<Role> roles;
    
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="money")
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	@Column(name = "time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	 
	@OneToOne(mappedBy="user",cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.REFRESH}) 
	public UserInformation getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}
	@ManyToMany
	@JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name = "role_id")})
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}  	
}
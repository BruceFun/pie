package com.pie.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pie.commons.IdEntity;

@Entity
@Table(name = "role")
public class Role extends IdEntity{

	private static final long serialVersionUID = 1L;

	private String name;
	// 一个角色对应多个权限
	private List<Permission> permissions;
	// 一个角色对应多个用户
	private List<User> users;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy = "role")
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	@ManyToMany
	@JoinTable(name = "user_role",joinColumns = {@JoinColumn(name = "role_id")},inverseJoinColumns = {@JoinColumn(name = "user_id")})
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}

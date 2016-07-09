package com.pie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pie.commons.IdEntity;

@Entity
@Table(name = "permission")
public class Permission extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	private String name;
	// һ��Ȩ�޶�Ӧһ����ɫ
	private Role role;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	@JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}

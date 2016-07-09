package com.pie.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pie.commons.IdEntity;

/**
 * 数据字典详情实体
 * @author bruce_000
 */
@Entity
@Table(name = "system_dictionary_details")
public class SystemDictionaryDetails extends IdEntity{

	private static final long serialVersionUID = 1L;
	private String name;
	private SystemDictionaryType type;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(cascade = CascadeType.REFRESH)  
	@JoinColumn(name="type_id")//这里设置JoinColum设置了外键的名字，并且orderItem是关系维护端 
	public SystemDictionaryType getType() {
		return type;
	}
	public void setType(SystemDictionaryType type) {
		this.type = type;
	}
}

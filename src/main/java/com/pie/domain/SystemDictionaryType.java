package com.pie.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pie.commons.IdEntity;

/**
 * 数据字典类型
 * @author bruce_000
 */
@Entity
@Table(name = "system_dictionary_type")
public class SystemDictionaryType extends IdEntity{

	private static final long serialVersionUID = 1L;
	private String name;
	// 1-支出   2-收入
	private String type;
	
	private Set<ItemDetails> ItemDetails;
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	@Column(name="type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="type")
	@JsonIgnore
	public Set<ItemDetails> getItemDetails() {
		return ItemDetails;
	}

	public void setItemDetails(Set<ItemDetails> itemDetails) {
		ItemDetails = itemDetails;
	}
	
}

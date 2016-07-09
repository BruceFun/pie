package com.pie.commons;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 统一定义ID 的Entity 基类
 * 基类统一定义ID 的属性名称�?数据类型、列名映射及生成策略
 * @author bruce_000
 *
 */
//JPA 基类的标�?
@MappedSuperclass
public class IdEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String id;
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
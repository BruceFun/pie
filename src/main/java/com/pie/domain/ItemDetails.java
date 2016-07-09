package com.pie.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pie.commons.IdEntity;

/**
 * ֧����������ϸ��
 * @author bruce_000
 *
 */
@Entity
@Table(name="item_details")
public class ItemDetails extends IdEntity{

	private static final long serialVersionUID = 1L;
	// ֧����������Ŀ
	private String item;
	// ���
	private BigDecimal money;
	// ֧��������ʱ��
	private Date payTime;
	// 1-֧�� 2-����
	private String sign;
	// ������Ϣ
	private String extraContent;
	// ����ʱ��
	private Date time = new Date();
	// ��ӦpayTime-��
	private String year;
	// ��ӦpayTime-��
	private String month;
	// ��ӦpayTime-��
	private String day;
	// ��Ŀ����
	private SystemDictionaryType type;
	// ��Ŀ������ϸ
	private SystemDictionaryDetails typeDetails;
	// ������
	private User user;
	
	@Column(name="item")
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	@Column(name="money")
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	@Column(name="pay_time")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	@Column(name="sign")
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	@Column(name="extra_content")
	public String getExtraContent() {
		return extraContent;
	}
	public void setExtraContent(String extraContent) {
		this.extraContent = extraContent;
	}
	@Column(name="time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Column(name="year")
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="month")
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	@Column(name="day")
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	@ManyToOne
	@JoinColumn(name = "type_id")
	public SystemDictionaryType getType() {
		return type;
	}
	public void setType(SystemDictionaryType type) {
		this.type = type;
	}
	@ManyToOne
	@JoinColumn(name = "details_id")
	public SystemDictionaryDetails getTypeDetails() {
		return typeDetails;
	}
	public void setTypeDetails(SystemDictionaryDetails typeDetails) {
		this.typeDetails = typeDetails;
	}
	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}

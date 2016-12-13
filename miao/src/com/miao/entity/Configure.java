package com.miao.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 存储系统配置信息
 * @author 
 *
 */
@Entity
@Table(name = "configure", catalog = "miao")
public class Configure implements java.io.Serializable {

	private Integer id;     //标识
	private String name;    //配置名
	private String val;     //配置值
	private String remark;  //备注

	public Configure() {}

	public Configure(String name, String val, String remark) {
		this.name = name;
		this.val = val;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "val")
	public String getVal() {
		return this.val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
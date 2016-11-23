package com.miao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 权限实体
 * 
 * @author Jupiter
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "power")
public class Power implements Serializable {
	// 权限id
	private Integer powerId;
	// 权限名
	private String powerName;
	// 权限与角色关系，多对多
	private Set<Role> roles = new HashSet<Role>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPowerId() {
		return powerId;
	}

	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	@ManyToMany(mappedBy = "powers")
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Power() {
		super();
	}

}

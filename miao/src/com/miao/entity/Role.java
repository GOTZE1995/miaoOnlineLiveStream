package com.miao.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 角色实体
 * 
 * @author Jupiter
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "role")
public class Role implements Serializable {

	// 角色id
	private Integer roleId;
	// 角色名
	private String roleName;
	// 角色与权限，多对多
	private Set<Power> powers = new HashSet<Power>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "rolepowerall", joinColumns = { @JoinColumn(name = "roleId") }, inverseJoinColumns = {
			@JoinColumn(name = "powerId") })
	public Set<Power> getPowers() {
		return powers;
	}

	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}

	public Role() {
	}

}

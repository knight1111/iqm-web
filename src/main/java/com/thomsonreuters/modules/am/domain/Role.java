package com.thomsonreuters.modules.am.domain;

import java.io.Serializable;

import com.thomsonreuters.common.persistence.DataEntity;

public class Role extends DataEntity<Role> implements Serializable {

	private String role;
	private String description;
	private Boolean available;
	
	public Role() {
		super();
	}
	
	public Role(Integer id){
		super(id);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role == null ? null : role.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}
}
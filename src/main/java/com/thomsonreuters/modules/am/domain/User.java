package com.thomsonreuters.modules.am.domain;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.thomsonreuters.common.persistence.DataEntity;
import com.thomsonreuters.common.utils.Collections3;

public class User extends DataEntity<User> implements Serializable {

	private String username;
	private String password;
	private String salt;
	private Integer locked = 0;

	private String oldPassword;
	private String newPassword;
	
	private String name;
	private String email;

	private Role role; // 根据角色查询用户条件
	private List<Role> roleList = Lists.newArrayList(); // 拥有角色列表

	public User() {
		super();
	}

	public User(Integer id) {
		super(id);
	}

	public User(Integer id, String username) {
		super(id);
		this.username = username;
	}

	public User(Role role) {
		super();
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getCredentialsSalt() {
		return username + salt;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "role", ",");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (id != null ? !id.equals(user.id) : user.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\''
				+ ", name='" + name + '\'' + ", salt='" + salt + '\''
				+ ", locked=" + locked + '}';
	}
}
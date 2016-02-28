package com.thomsonreuters.common.persistence;

import java.io.Serializable;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.thomsonreuters.common.config.GlobalConstants;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.utils.UserUtils;

public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	protected Integer id;

	/**
	 * currentUser
	 */
	protected User currentUser;

	/**
	 * sqlMap(key, content)
	 */
	protected Map<String, String> sqlMap;

	/**
	 * isNewRecord
	 */
	protected boolean isNewRecord = false;

	public BaseEntity() {

	}

	public BaseEntity(Integer id) {
		this();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	@XmlTransient
	public User getCurrentUser() {
		if (currentUser == null) {
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@JsonIgnore
	@XmlTransient
	public Map<String, String> getSqlMap() {
		if (sqlMap == null) {
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}

	/**
	 * preInsert
	 */
	public abstract void preInsert();

	/**
	 * preUpdate
	 */
	public abstract void preUpdate();

	/**
	 * getIsNewRecord
	 * 
	 * @return
	 */
	public boolean getIsNewRecord() {
		return isNewRecord || getId() == null;// StringUtils.isBlank(getId());
	}

	/**
	 * setIsNewRecord
	 */
	public void setIsNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}

	/**
	 * getGlobal
	 */
	@JsonIgnore
	public GlobalConstants getGlobal() {
		return GlobalConstants.getInstance();
	}

	/**
	 * getDbName
	 */
	@JsonIgnore
	public String getDbName() {
		return GlobalConstants.getConfig("jdbc.type");
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(obj.getClass())) {
			return false;
		}
		BaseEntity<?> that = (BaseEntity<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * Delete_flag (0:regular, 1:deleted, 2:review)
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";

}

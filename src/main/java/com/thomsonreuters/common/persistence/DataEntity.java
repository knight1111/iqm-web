package com.thomsonreuters.common.persistence;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thomsonreuters.modules.am.domain.User;
import com.thomsonreuters.modules.am.utils.UserUtils;

/**
 * DataEntity
 * @author 
 * @version 
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;	
	protected User createBy;	
	protected Date createDate;	
	protected User modifyBy;	
	protected Date modifyDate;	
	protected String delFlag; 	
	
	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntity(Integer id) {
		super(id);
	}
	
	/**
	 * preInsert
	 */
	@Override
	public void preInsert(){
		User user = UserUtils.getUser();
		this.modifyBy = user;
		this.createBy = user;
		this.modifyDate = new Date();
		this.createDate = this.modifyDate;
	}
	
	/**
	 * preUpdate
	 */
	@Override
	public void preUpdate(){
		User user = UserUtils.getUser();
		if (user.getId() != null){
			this.modifyBy = user;
		}
		this.modifyDate = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public User getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(User modifyBy) {
		this.modifyBy = modifyBy;
	}

	@JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}	

	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}

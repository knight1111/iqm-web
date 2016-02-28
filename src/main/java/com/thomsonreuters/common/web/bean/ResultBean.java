package com.thomsonreuters.common.web.bean;

import com.thomsonreuters.common.config.GlobalConstants;

public class ResultBean {

	private String isSuccess;
	private String errorMessage;
	
	public ResultBean(){
		this.isSuccess = GlobalConstants.SUCCESS;
	}
	
	public ResultBean(String isSuccess, String errorMessage){
		this.isSuccess = isSuccess;
		this.errorMessage = errorMessage;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
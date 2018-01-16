package com;

/**
 * @author RITESH SINGH
 *
 */
public class Response {

	private int status;
	private String statusDescription;
	private Object data;
	private Object additionalData;
	
	public Response(int status, String statusDescription, Object data){
		this.status = status;
		this.statusDescription = statusDescription;
		this.data = data;
	}
	
	public Response(int status, String statusDescription, Object data, Object additionalData){
		this.status = status;
		this.statusDescription = statusDescription;
		this.data = data;
		this.additionalData = additionalData;
	}
	
	public Response(){}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Object getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(Object additionalData) {
		this.additionalData = additionalData;
	}
}

package com;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class Response<T> {

	private int status;
	private String message;
	private T data;

	public Response() {
		super();
	}

	public Response(int status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
}


/*public class Response {

	private Integer status;
	private String statusDescription;
	private Object data;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
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
	
}*/
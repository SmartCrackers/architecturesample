package com.modelUtility;

import java.util.Calendar;

/**
 * @author RITESH SINGH
 *
 */
public class EditableInfo {

	private long createdAt;
	private String createdBy;
	private long updatedAt;
	
	private String updatedBy;
	
	public EditableInfo(){
		this.createdAt = Calendar.getInstance().getTimeInMillis();
		this.updatedAt = this.createdAt;
	}
	
	public long getCreatedAt() {
		return createdAt;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public long getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt() {
		this.updatedAt = Calendar.getInstance().getTimeInMillis();
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}

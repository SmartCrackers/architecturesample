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
	
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt() {
		long createdAt = Calendar.getInstance().getTimeInMillis();
		this.createdAt = createdAt;
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
		long updatedAt = Calendar.getInstance().getTimeInMillis();
		this.updatedAt = updatedAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}

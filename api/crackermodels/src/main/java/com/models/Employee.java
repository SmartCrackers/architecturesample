package com.models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author RITESH SINGH
 *
 */
@Document(collection = "employees")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 7746218756589213487L;

	@Id
	private Long id;
	
	private String name;

	private BigDecimal salary;

	private String ssn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}

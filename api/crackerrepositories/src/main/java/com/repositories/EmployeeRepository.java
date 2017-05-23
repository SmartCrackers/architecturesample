package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.models.Employee;

/**
 * @author RITESH SINGH
 *
 */
public interface EmployeeRepository extends MongoRepository<Employee, Long> {

}

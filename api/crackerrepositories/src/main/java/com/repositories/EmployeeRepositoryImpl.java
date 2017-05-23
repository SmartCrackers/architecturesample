package com.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.models.Employee;

/**
 * @author RITESH SINGH
 *
 */
@Repository("employeeRepository")
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Override
	public <S extends Employee> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		try{
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "_id"));
	
			List<Employee> employees=  mongoTemplate.find(query, Employee.class);
			
			return employees;
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public List<Employee> findAll(Sort sort) {
		// TODO Auto-generated method stub
		try{
			Query query = new Query();
			//query.with(new Sort(Sort.Direction.DESC, "_id"));
			query.with(sort);
	
			List<Employee> employees=  mongoTemplate.find(query, Employee.class);
			
			return employees;
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public <S extends Employee> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Employee> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		/*Query query = new Query();
		   query.addCriteria(Criteria.where("category").is(category));*/
		
		try{	
			return mongoTemplate.count(new Query(), Employee.class);
		}catch(Exception ee){}
		
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Employee> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Employee> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Employee findOne(Long arg0) {
		
		return mongoTemplate.findById(arg0, Employee.class);
	}

	@Override
	public <S extends Employee> S save(S arg0) {
		// TODO Auto-generated method stub
		try{
			mongoTemplate.save(arg0);
		}catch(Exception ee){
			
		}
		
		return arg0;
	}

	@Override
	public <S extends Employee> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		
		return 0;
	}

	@Override
	public <S extends Employee> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Employee> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> S findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}

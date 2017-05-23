package com.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.models.Employee;
import com.models.User;

/**
 * @author RITESH SINGH
 *
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Override
	public <S extends User> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		try{
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "_id"));
	
			List<User> users=  mongoTemplate.find(query, User.class);
			
			return users;
			
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public List<User> findAll(Sort sort) {
		try{
			Query query = new Query();
			//query.with(new Sort(Sort.Direction.DESC, "_id"));
			query.with(sort);
	
			List<User> users=  mongoTemplate.find(query, User.class);
			
			return users;
		}catch(Exception ee){}
		return null;
	}

	@Override
	public <S extends User> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		try{	
			return mongoTemplate.count(new Query(), User.class);
		}catch(Exception ee){}
		
		return 0;
	}

	@Override
	public void delete(Long arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends User> arg0) {
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
	public Iterable<User> findAll(Iterable<Long> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(Long arg0) {
		return mongoTemplate.findById(arg0, User.class);
	}
	
	@Override
	public User getUserByUserName(String userName) {
		
		if(userName == null)
			return null;
		
		Query query = new Query();
		   query.addCriteria(Criteria.where("userName").is(userName));
		try{
			return mongoTemplate.findOne(query, User.class);
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public <S extends User> S save(S arg0) {
		try{
			mongoTemplate.save(arg0);
		}catch(Exception ee){
			
		}
		
		return arg0;
	}

	@Override
	public <S extends User> long count(Example<S> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> S findOne(Example<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}

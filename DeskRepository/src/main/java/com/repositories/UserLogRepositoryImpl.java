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

import com.exception.BookException;
import com.models.User;
import com.models.UserLog;

/**
 * @author RITESH SINGH
 *
 */
@Repository("userLogRepository")
public class UserLogRepositoryImpl implements UserLogRepository {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private UserLogDao userLogDao;

	@Override
	public <S extends UserLog> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserLog> findAll() {
		try{
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "_id"));
			List<UserLog> userLogs=  mongoTemplate.find(query, UserLog.class);
			return userLogs;
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public List<UserLog> findAll(Sort sort) {
		try{
			Query query = new Query();
			query.with(sort);
			List<UserLog> userLogs=  mongoTemplate.find(query, UserLog.class);
			return userLogs;
		}catch(BookException ee){ throw ee;}
	}

	@Override
	public <S extends UserLog> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserLog> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserLog> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserLog> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<UserLog> findAll(Pageable pageable) {
		try{
			return userLogDao.findAll(pageable);
		}catch(BookException ee){ throw ee;}
	}

	@Override
	public <S extends UserLog> S save(S entity) {
		try{
			return userLogDao.save(entity);
		}catch(BookException ee){ throw ee;}
	}

	@Override
	public UserLog findOne(String id) {
		try{
			return userLogDao.findOne(id);
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public boolean exists(String id) {
		try{
			return userLogDao.exists(id);
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public Iterable<UserLog> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		
		try{
			return userLogDao.count();
		}catch(BookException e){
			throw e;
		}
	}

	@Override
	public void delete(String id) {
		try{
			userLogDao.delete(id);
		}catch(BookException e){
			throw e;
		}
	}

	@Override
	public void delete(UserLog entity) {
		try{
			userLogDao.delete(entity);
		}catch(BookException e){
			throw e;
		}
	}

	@Override
	public void delete(Iterable<? extends UserLog> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		try{
			userLogDao.deleteAll();
		}catch(BookException e){
			throw e;
		}
	}

	@Override
	public <S extends UserLog> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserLog> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends UserLog> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends UserLog> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserLog findUserLogByUserName(String userName) {
		try{
			return userLogDao.findUserLogByUserName(userName);
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public UserLog findUserLogByEmail(String email) {
		try{
			return userLogDao.findUserLogByEmail(email);
		}catch(BookException e){
			throw e;
		}
	}

	@Override
	public UserLog findUserLogByUserNameAndEmail(String userName, String email) {
		try{
			return userLogDao.findUserLogByUserNameAndEmail(userName, email);
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public UserLog findUserLogByUserNameAndEmailAndIsExpired(String userName, String email, Boolean isExpired) {
		try{
			return userLogDao.findUserLogByUserNameAndEmailAndIsExpired(userName, email, isExpired);
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public UserLog findUserLogByToken(String token) {
		try{
			return userLogDao.findUserLogByToken(token);
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public UserLog findUserLogByTokenAndUserName(String token, String userName) {
		try{
			
			return userLogDao.findUserLogByTokenAndUserName(token, userName);
		}catch(BookException ee){
			throw ee;
		}
	}

	@Override
	public UserLog findUserLogByTokenAndEmail(String token, String email) {
		try{
			
			return userLogDao.findUserLogByTokenAndEmail(token, email);
		}catch(BookException ee){
			throw ee;
		}
	}
}

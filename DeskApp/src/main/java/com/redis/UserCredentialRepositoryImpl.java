package com.redis;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.redis.RedisError;

@Repository("userCredentialRepository")
public class UserCredentialRepositoryImpl implements UserCredentialRepository {

	final static Logger logger = Logger.getLogger(UserCredentialRepositoryImpl.class);
	
	private static final String KEY = "UserCredentialData";

    private RedisTemplate<String, UserCredential> redisTemplate;
    private HashOperations hashOps;
	
    @Autowired
    private UserCredentialRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
	
    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
    
	@Override
	public void save(UserCredential userCredential) {
		try{
			hashOps.put(KEY, userCredential.getUserName(), userCredential);
		}catch(RedisError e){
			logger.fatal("error while saving token in redis.");
			throw new RedisError(e);
		}
	}
	
	@Override
    public void update(UserCredential userCredential) {
        
		try{
			hashOps.put(KEY, userCredential.getUserName(), userCredential);
		}catch(RedisError e){
			logger.fatal("error while updating token  from redis.");
			throw new RedisError(e);
		}
    }
	
	@Override
	public UserCredential findByToken(String userName) {
		
		try{
			return (UserCredential) hashOps.get(KEY, userName);
		}catch(RedisError e){
			logger.fatal("error while getting token  from redis.");
			throw new RedisError(e);
		}
	}
	
	@Override
    public Map<Object, Object> findAllTokens() {
        try{
        	return hashOps.entries(KEY);
        }catch(RedisError e){
			logger.fatal("error while getting all tokens  from redis.");
			throw new RedisError(e);
		}
    }
	
	@Override
	public void deleteStudent(String token) {
        try{
        	hashOps.delete(KEY, token);
        }catch(RedisError e){
			logger.fatal("error while deleting all token from redis.");
			throw new RedisError(e);
		}
    }
}

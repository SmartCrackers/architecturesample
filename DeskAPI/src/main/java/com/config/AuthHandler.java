package com.config;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.Constants;
import com.exception.BookException;
import com.google.gson.Gson;
import com.models.UserLog;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Configuration
public class AuthHandler {

	@Bean(initMethod = "init", destroyMethod = "cleanup" )
	public TokenHandlerImpl tokenHandlerImpl(){
		return new TokenHandlerImpl();
	}
}

interface TokenHandler{
	
	int isTokenExpired(String token);
}

class TokenHandlerImpl implements TokenHandler {

	private MongoClient mongo;
	private DB db; 
	private DBCollection collection;
	
	public void init() throws UnknownHostException {
		
		this.mongo = new MongoClient( "127.0.0.1" , 27017 );
		this.db = this.mongo.getDB("demo-test");
		this.collection = this.db.getCollection("users_log");
		
		System.out.println("Init Token");
	}
	
	@Override
	public int isTokenExpired(String token) {
		try{
			if(token!=null){
				UserLog userLog = this.getUserByToken(token);
				
				if(userLog!=null){
					if(userLog.tokenStatus() == Constants.EXPIRED_TOKEN){
						this.updateTokenExpireStatus(userLog);
					}
					return userLog.tokenStatus();
				}
				return 1;
			}
			return Constants.BAD_TOKEN;
		}catch(Exception e){
			String message = String.format("Error while checking isTokenExpired @TokenHandler.");
			throw new BookException(message, e);
		}
	}
	
	private void updateTokenExpireStatus(UserLog log){
		try{
		
			if(log!=null && !log.getIsExpired()){
				BasicDBObject searchObject = new BasicDBObject();
				searchObject.put("token", log.getToken());
				
				DBObject updatedObject =  this.collection.find(searchObject).toArray().get(0);
				updatedObject.put("isExpired", true);
				updatedObject.put("expiredType", Constants.TIMESTAMP_TOEKN_EPIRE_TYPE);
				this.collection.findAndModify(searchObject, updatedObject);
			}
		}catch(Exception ee){
			String message = String.format("Error while updating token epired @timestamp @TokenHandler.");
			throw new BookException(message, ee);
		}
	}
	
	private UserLog getUserByToken(String token){
		
		UserLog userLog = null;
		try{
			BasicDBObject dbObject = new BasicDBObject();
			dbObject.put("token", token);
			
			List<DBObject> obj = this.collection.find(dbObject).toArray();
			if(obj.size() <= 0){
				return userLog;
			}
			DBObject newObject =  obj.get(0);
			Gson gson = new Gson();
			try{
				userLog = gson.fromJson(gson.toJson(newObject), UserLog.class);
			}catch(Exception ee){
				String message = String.format("Error while parsing json into UserLog class in get userLog by token @TokenHandler.");
				throw new BookException(message, ee);
			}
			
			return userLog;
			
		}catch(Exception e){
			e.printStackTrace();
			String message = String.format("Error while fetching user by token @TokenHandler.");
			throw new BookException(message, e);
		}
	}
   
	public void cleanup() {
		System.out.println("cleanup Token");
		mongo.close();
   }
}
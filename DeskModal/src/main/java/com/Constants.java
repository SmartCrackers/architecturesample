package com;

public class Constants {

	public final static String REQUEST_TYPE_WEB = "web";
	public final static String REQUEST_TYPE_APP = "app";
	public final static String HEADER_KEY = "X-AUTH-HEADER";
	
	public static final long TOKEN_LIFE = 1000*60*3;
	
	public static final String FOLDER_BOOK = "book";
	public static final int BAD_TOKEN = -1;
	public static final int EXPIRED_TOKEN = 0;
	public static final int ACTIVE_TOKEN = 1;
	
	public static final int LOGOUT_TOEKN_EPIRE_TYPE = 1;
	public static final int TIMESTAMP_TOEKN_EPIRE_TYPE = 2;
	public static final int FORCED_TOEKN_EPIRE_TYPE = 3;
	
}

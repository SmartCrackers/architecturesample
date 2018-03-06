package com.services;

import com.models.User;
import com.models.UserLog;

public interface AsynchDeltaService {

	Integer createAccessLog(User user);

}

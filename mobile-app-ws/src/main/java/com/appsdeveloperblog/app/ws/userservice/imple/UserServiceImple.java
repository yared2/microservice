package com.appsdeveloperblog.app.ws.userservice.imple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.UserService;

@Service
public class UserServiceImple implements UserService{

	Map<String, UserRest> users;
	private Utils util;
	public UserServiceImple(Utils util) {
		this.util =util;
	}
	
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		String userId = util.generetUserId();
		if (users == null) {
			users = new HashMap<>();
			
			returnValue.setUserId(userId);
			users.put(userId, returnValue);
		}

		return returnValue;
	}
	}



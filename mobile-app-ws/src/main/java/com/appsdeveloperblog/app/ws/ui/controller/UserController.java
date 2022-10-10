package com.appsdeveloperblog.app.ws.ui.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.imple.UserServiceImple;

@RestController
@RequestMapping("users")
public class UserController {

	Map<String, UserRest> users;
	
	@Autowired
	private UserServiceImple userService;

	@GetMapping
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit) {
		return "get user was caller = " + page + "limit =" + limit;
	}

	@GetMapping(path = "/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
		
//		if(true) {
//			throw new UserServiceException("A user service exception is thrown");
//		}
		
		if (users.containsKey(userId)) {
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> CreateUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
	
		UserRest returnValue = userService.createUser(userDetails);
		

		return new ResponseEntity<UserRest>(returnValue, HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/{userId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRest> updateuser(@PathVariable("userId") String userId,
			@Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		UserRest storedUserdetails = users.get(userId);
		storedUserdetails.setFirstName(userDetails.getFirstName());
		storedUserdetails.setLastName(userDetails.getLastName());
		users.put(userId, storedUserdetails);
		return new  ResponseEntity<UserRest>(storedUserdetails,HttpStatus.ACCEPTED);
		
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}

}

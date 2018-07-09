
package com.monthly.expenses.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monthly.expenses.constant.AppConstants;
import com.monthly.expenses.domain.User;
import com.monthly.expenses.exception.EmailExistException;
import com.monthly.expenses.exception.ResourceNotFoundException;
import com.monthly.expenses.model.Response;
import com.monthly.expenses.service.EmailService;
import com.monthly.expenses.service.UserService;

/**
 * The Class PreLoginController.
 *
 * @author G Lokesh
 */
@RestController
public class PreLoginController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;
	
	@Autowired
	ServletContext context;

	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		if (user != null) {
			if (StringUtils.hasText(user.getEmail())) {
				User dbUser = userService.findByEmail(user.getEmail());
				if (dbUser != null && dbUser.getId() != null) {
					throw new EmailExistException("email is already registered");
				}
			}
			user.setCreatedBy(AppConstants.SELF_USER);
			user.setRole(AppConstants.ROLE_USER);
			user.setIsActivated(true);
			user.setCreatedDate(new Date());
		}

		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
	}

	/**
	 * Forgot password.
	 *
	 * @param user
	 *            the user
	 * @return the response
	 */
	@PostMapping("/forgotpassword")
	public Response forgotPassword(@RequestBody final User user) {
		if (user != null && StringUtils.hasText(user.getEmail())) {
			User dbuser = userService.findByEmail(user.getEmail());
			if (dbuser != null) {
				userService.resetPassword(dbuser);
				return Response.success("Your new password has been sent to your registered email id");
			} else {
				return Response.error("email is not found in our server", 404, "email not found");
			}
		}
		return null;

	}

	/**
	 * Change password.
	 *
	 * @param userDTO
	 *            the user DTO
	 * @return the response
	 */
	@GetMapping("/checkemail/{email:.+}")
	public ResponseEntity<User> checkemail(@PathVariable String email) {
		User dbUser = userService.findByEmail(email);
		if (dbUser != null) {
			if(StringUtils.hasText(dbUser.getBackgroundImageName())) {
				String modifiedFilePath =context.getRealPath("/userprofile/"+File.separator+dbUser.getBackgroundImageName());
				File file = new File(modifiedFilePath);
				if(!file.exists()) {
					dbUser.setBackgroundImageName("");
				}
			}else {
				dbUser.setBackgroundImageName("");
			}
			
			if(StringUtils.hasText(dbUser.getUserProfileName())) {
				String modifiedFilePath =context.getRealPath("/userprofile/"+File.separator+dbUser.getUserProfileName());
				File file = new File(modifiedFilePath);
				if(!file.exists()) {
					dbUser.setUserProfileName("");
				}
			}else {
				dbUser.setUserProfileName("");
			}
			
			return new ResponseEntity<User>(dbUser, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("user is not exist");
		}

	}
	
	
	@PostMapping("/checkSocialEmail")
	public ResponseEntity<User> checkSocialEmail(@RequestBody User user) {
		if (user != null) {
			if (StringUtils.hasText(user.getEmail())) {
				User dbUser = userService.findByEmail(user.getEmail());
				if (dbUser != null && dbUser.getId() != null) {
					return new ResponseEntity<User>(dbUser, HttpStatus.OK);
				}else {
					user.setCreatedBy(AppConstants.SELF_USER);
					user.setRole(AppConstants.ROLE_USER);
					user.setIsActivated(true);
					user.setCreatedDate(new Date());
					return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
				}
			}else {
				throw new ResourceNotFoundException("user email can't be null");
			}
			
		}else {
			throw new ResourceNotFoundException("user object can't be null");
		}

		
	}
	
	
	
	
	
	
	
	
	
}

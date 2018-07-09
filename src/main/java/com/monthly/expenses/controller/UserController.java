package com.monthly.expenses.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monthly.expenses.domain.User;
import com.monthly.expenses.model.Response;
import com.monthly.expenses.model.UserDTO;
import com.monthly.expenses.service.EmailService;
import com.monthly.expenses.service.UserService;
import com.monthly.expenses.util.PasswordUtil;
import com.monthly.expenses.util.UserUtil;

/**
 * The Class UserController.
 *
 * @author G Lokesh
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EmailService emailService;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired 
    ServletContext context;
    
    
    /**
     * Update.
     *
     * @param user
     *            the user
     * @return the response entity
     */
    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> update(@RequestBody User user) {
    	User loginuser = UserUtil.getAuthenticatedUser();
    	if(loginuser!=null) {
    		user.setUpdatedBy(loginuser.getRole());
    		user.setUpdatedDate(new Date());
    	}
    	
    	User dbUser = userService.updateUser(user);
        return new ResponseEntity<User>(dbUser, HttpStatus.OK);
    }
    
    
    @PostMapping("/userprofile")
    @PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> userprofile(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
    	User user = userService.findById(userId);
    	String userProfileName = userService.updateuserprofile(file);
    	File mfile =  new File(context.getRealPath("/userprofile/"+File.separator+user.getUserProfileName()));
    	if(mfile.exists()) {
    		mfile.delete();
    	}
    	
    	user.setUserProfileName(userProfileName);
    	User dbUser = userService.updateUser(user);
        return new ResponseEntity<User>(dbUser, HttpStatus.OK);
	}
    
    @PostMapping("/userbackground")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> userbackground(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
    	User user = userService.findById(userId);
    	String backgroundImageName = userService.updateuserprofile(file);
    	File mfile =  new File(context.getRealPath("/userprofile/"+File.separator+user.getBackgroundImageName()));
    	if(mfile.exists()) {
    		mfile.delete();
    	}
    	user.setBackgroundImageName(backgroundImageName);
    	User dbUser = userService.updateUser(user);
        return new ResponseEntity<User>(dbUser, HttpStatus.OK);
    }
    
    @PostMapping("/usersignature")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> usersignature(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
    	User user = userService.findById(userId);
    	String signatureName = userService.updateuserprofile(file);
    	user.setSignatureName(signatureName);
    	User dbUser = userService.updateUser(user);
        return new ResponseEntity<User>(dbUser, HttpStatus.OK);
    }
    
    /**
     * Users.
     *
     * @return the response entity
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<User>> users() {
         List<User> users = userService.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    /**
     * Change password.
     *
     * @param userDTO
     *            the user DTO
     * @return the response
     */
    @PostMapping("/changePassword")
    @PreAuthorize("hasRole('USER')")
    public Response changePassword(@RequestBody UserDTO userDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUser().getEmail(), userDTO.getUser().getPassword()));
        User dbUser = userService.findById(userDTO.getUser().getId());
        dbUser.setPassword(PasswordUtil.getPasswordHash(userDTO.getNewPassword()));
        dbUser.setIsTempPassword(false);
        dbUser.setUpdatedDate(new Date());
        dbUser.setPasswordChangedDate(new Date());
        userService.updateUser(dbUser);
        return Response.success("Password has been changed successfully");
    }
    
}

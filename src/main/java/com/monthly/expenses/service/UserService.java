
package com.monthly.expenses.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.monthly.expenses.domain.User;


/**
 * The Interface UserService.
 *
 * @author G Lokesh
 */
public interface UserService {
	
	/**
     * Creates the user.
     *
     * @param user
     *            the user
     * @return the user
     */
    User createUser(User user);
	
    /**
     * Update user.
     *
     * @param user
     *            the user
     * @return the user
     */
    User updateUser(User user);
    
    /**
     * Find all.
     *
     * @return the list
     */
    List<User> findAll();
    
    /**
     * Find by id.
     *
     * @param userId
     *            the user id
     * @return the user
     */
    User findById(Long userId);
    
    /**
     * Find by email.
     *
     * @param email
     *            the email
     * @return the optional
     */
    User findByEmail(String email);
    
    /**
     * @param userProfile
     * @return
     */
    String updateuserprofile(MultipartFile userProfile);
    
    /**
     * @param user
     * @return
     */
    User resetPassword(User user);

   
}
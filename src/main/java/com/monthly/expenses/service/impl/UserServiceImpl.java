
package com.monthly.expenses.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.monthly.expenses.domain.User;
import com.monthly.expenses.repository.UserRepository;
import com.monthly.expenses.service.EmailService;
import com.monthly.expenses.service.UploadPathService;
import com.monthly.expenses.service.UserService;
import com.monthly.expenses.util.PasswordUtil;

/**
 * The Class UserServiceImpl.
 *
 * @author G Lokesh
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	UploadPathService uploadPathService;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.UserService#createUser(
	 * com.springboot.angular.domain.User)
	 */
	@Override
	public User createUser(User user) {
		String password = "";
		if (StringUtils.isEmpty(user.getPassword())) {
			password = PasswordUtil.getTempPassword();
			user.setOrginalPassword(password);
			user.setPassword(PasswordUtil.getPasswordHash(password));
			user.setIsTempPassword(true);
		} else {
			password = user.getPassword();
			user.setOrginalPassword(password);
			user.setPassword(PasswordUtil.getPasswordHash(user.getPassword()));
			user.setIsTempPassword(false);
		}

		user = userRepository.save(user);
		emailService.sendUserRegistration(user, password);
		return user;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.UserService#updateUser(
	 * com.springboot.angular.domain.User)
	 */
	@Override
	public User updateUser(User user) {
		User dbUser = null;
		dbUser = userRepository.findOne(user.getId());
		if (dbUser == null) {
			throw new UsernameNotFoundException("Invalid user");
		}
		user.setPassword(dbUser.getPassword());
		user.setOrginalPassword(dbUser.getOrginalPassword());
		user.setEmail(dbUser.getEmail());
		user.setCreatedDate(dbUser.getCreatedDate());
		user.setCreatedBy(dbUser.getCreatedBy());
		dbUser = userRepository.save(user);
		return dbUser;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.UserService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAllByOrderByFirstNameAsc();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.UserService#findById(
	 * java.lang.Long)
	 */
	@Override
	public User findById(Long userId) {
		return userRepository.findOne(userId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.UserService#findByEmail
	 * (java.lang.String)
	 */
	@Override
	public User findByEmail(String email) {
		return userRepository.FindByEmailIgnoreCase(email);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.UserService#updateuserprofile
	 * (org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String updateuserprofile(MultipartFile mFile) {
		if (mFile != null) {
			String imageName = mFile.getOriginalFilename();
			String modifiedImageName = FilenameUtils.getBaseName(imageName) + "_" + System.currentTimeMillis() + "."
					+ FilenameUtils.getExtension(imageName);
			File file = uploadPathService.getFilesPath(modifiedImageName, "userprofile");
			if (file != null) {
				try {
					FileUtils.writeByteArrayToFile(file, mFile.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return modifiedImageName;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.UserService#
	 * updateUserPassword(com.springboot.angular.domain.User)
	 */
	@Override
	public User resetPassword(User dbUser) {
		if (dbUser != null) {
			String tempPassword = PasswordUtil.getTempPassword();
			dbUser.setOrginalPassword(tempPassword);
			dbUser.setPassword(PasswordUtil.getPasswordHash(tempPassword));
			dbUser.setIsTempPassword(true);
			dbUser.setUpdatedDate(new Date());
			dbUser.setPasswordChangedDate(new Date());
			emailService.sendForgotPassword(userRepository.save(dbUser), tempPassword);
		} else {
			throw new UsernameNotFoundException("Email not found");
		}
		return dbUser;
	}
}

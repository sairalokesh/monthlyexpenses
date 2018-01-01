
package com.monthly.expenses.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.monthly.expenses.domain.User;
import com.monthly.expenses.security.JwtUser;

/**
 * The Class UserUtil.
 * 
 * @author G Lokesh
 */
public class UserUtil {

	/**
	 * @param authentication
	 * @return
	 */
	public static User getAuthenticatedUser() {
		try {
			Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (userDetails != null && userDetails instanceof JwtUser) {
				return ((JwtUser) userDetails).getUser();
			}
		} catch (NullPointerException npe) {
		}
		return null;
	}

}
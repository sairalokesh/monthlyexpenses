
package com.monthly.expenses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monthly.expenses.domain.User;

/**
 * The Interface UserRepository.
 *
 * @author G Lokesh
 */
@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
     * Find by all order by.
     *
     * @param order by name
     * @return the optional
     */
    public List<User> findAllByOrderByFirstNameAsc();
	
    /**
     * Find by email ignore case.
     *
     * @param email
     *            the email
     * @return the optional
     */
    @Query("SELECT u FROM User u where u.email = :email")
    public User FindByEmailIgnoreCase(@Param("email") String email);
    
}

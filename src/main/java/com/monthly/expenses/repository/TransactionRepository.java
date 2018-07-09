package com.monthly.expenses.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.model.DbGraphDTO;
import com.monthly.expenses.model.DbTransactionDTO;
import com.monthly.expenses.model.StatisticDTO;

/**
 * The Interface TransactionRepository.
 *
 * @author G Lokesh
 */
@Transactional
@Repository
public interface TransactionRepository extends CrudRepository<Transactions, Long> {
	
	/**
     * Find by all order by.
     *
     * @param order by name
     * @return the optional
     */
    public List<Transactions> findAllByOrderByCategoryAsc();

	@Query("select new com.monthly.expenses.model.StatisticDTO(sum(t.creditAmount), sum(t.debitAmount)) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId")
	public StatisticDTO getStatistics(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId);

	@Query("select t from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId order by t.transactionDate desc")
	public List<Transactions> getTransactions(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId);

	@Query("select new com.monthly.expenses.model.DbGraphDTO(t.type, sum(t.creditAmount), sum(t.debitAmount), date_format(t.transactionDate,'%Y')) from Transactions t where t.user.id= :userId and t.type= :type group by date_format(t.transactionDate,'%Y'), t.type order by date_format(t.transactionDate,'%Y') desc")
	public List<DbGraphDTO> getYearlyTransaction(@Param("userId") Long userId, @Param("type") String type);

	@Query("select distinct(date_format(t.transactionDate,'%Y')) from Transactions t where t.user.id= :userId order by date_format(t.transactionDate,'%Y') desc")
	public List<String> getAllYears(@Param("userId") Long userId);

	@Query("select new com.monthly.expenses.model.DbGraphDTO(t.type, sum(t.creditAmount), sum(t.debitAmount), date_format(t.transactionDate,'%m')) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId  and t.type = :type group by date_format(t.transactionDate,'%m'), t.type order by date_format(t.transactionDate,'%m') desc")
	public List<DbGraphDTO> getMonthlyTransaction(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId, @Param("type") String type);

	@Query("select distinct(date_format(t.transactionDate,'%m')) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId order by date_format(t.transactionDate,'%m') desc")
	public List<String> getAllMonths(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId);

	@Query("select new com.monthly.expenses.model.DbGraphDTO(t.category, sum(t.debitAmount)) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId and t.type = :type group by t.category")
	public List<DbGraphDTO> getMonthlyChart(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId, @Param("type") String type);
	
	@Query("select new com.monthly.expenses.model.DbTransactionDTO(sum(t.creditAmount), sum(t.debitAmount), date_format(t.transactionDate,'%Y-%m')) from Transactions t where t.user.id= :userId group by date_format(t.transactionDate,'%Y-%m') order by date_format(t.transactionDate,'%Y-%m') desc")
	public List<DbTransactionDTO> getAllTransactionsByMontyYear(@Param("userId") Long userId);

	@Query("select new com.monthly.expenses.model.DbGraphDTO(t.type, sum(t.creditAmount), sum(t.debitAmount), date_format(t.transactionDate,'%d')) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId  and t.type = :type group by date_format(t.transactionDate,'%d'), t.type order by date_format(t.transactionDate,'%d') asc")
	public List<DbGraphDTO> getMonthlytransactionsCount(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId, @Param("type") String type);

	@Query("select distinct(date_format(t.transactionDate,'%d')) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId order by date_format(t.transactionDate,'%d') asc")
	public List<String> getAllDays(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId);
	
	
	@Query("select new com.monthly.expenses.model.DbGraphDTO(t.type, sum(t.creditAmount), sum(t.debitAmount), date_format(t.transactionDate,'%Y-%m')) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId  and t.type = :type group by date_format(t.transactionDate,'%Y-%m'), t.type order by date_format(t.transactionDate,'%Y-%m') desc")
	public List<DbGraphDTO> getRangetransactionsCount(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId, @Param("type") String type);
	
	@Query("select distinct(date_format(t.transactionDate,'%Y-%m')) from Transactions t where t.transactionDate>= :startDate and t.transactionDate<= :endDate and t.user.id= :userId order by date_format(t.transactionDate,'%Y-%m') desc")
	public List<String> getAllYearsAndMonths(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userId") Long userId);

	@Query("select new com.monthly.expenses.model.DbGraphDTO(t.type, sum(t.creditAmount), sum(t.debitAmount), date_format(t.transactionDate,'%Y-%m')) from Transactions t where t.category= :category and t.user.id= :userId and t.type = :type group by date_format(t.transactionDate,'%Y-%m'), t.type order by date_format(t.transactionDate,'%Y-%m') desc")
	public List<DbGraphDTO> getcategoryRangetransactionsCount(@Param("category") String category, @Param("userId") Long userId, @Param("type") String type);

	@Query("select distinct(date_format(t.transactionDate,'%Y-%m')) from Transactions t where t.category= :category and t.user.id= :userId order by date_format(t.transactionDate,'%Y-%m') desc")
	public List<String> getAllCategoryYearsAndMonths(@Param("category") String category, @Param("userId") Long userId);

	@Query("select distinct(t.type) from Transactions t where t.category= :category")
	public String getCategoryType(@Param("category") String category);
	
	@Query("select new com.monthly.expenses.domain.Transactions(t.id, t.description, t.transactionDate, t.location, t.latitude, t.longitude, t.creditAmount) from Transactions t where t.category= :category and t.user.id= :userId and t.type = :type order by t.transactionDate desc")
	public List<Transactions> getCategoryIncomeTransactions(@Param("category") String category, @Param("userId") Long userId, @Param("type") String type);
	
	@Query("select new com.monthly.expenses.domain.Transactions(t.id, t.description, t.transactionDate, t.location, t.latitude, t.longitude, t.debitAmount) from Transactions t where t.category= :category and t.user.id= :userId and t.type = :type order by t.transactionDate desc")
	public List<Transactions> getCategoryExpensesTransactions(@Param("category") String category, @Param("userId") Long userId, @Param("type") String type);
	
	@Query("select new com.monthly.expenses.model.StatisticDTO(sum(t.creditAmount)) from Transactions t where t.category= :category and t.user.id= :userId and t.type = :type")
	public StatisticDTO getCategoryIncomeStatistics(@Param("category") String category, @Param("userId") Long userId, @Param("type") String type);
	
	@Query("select new com.monthly.expenses.model.StatisticDTO(sum(t.debitAmount)) from Transactions t where t.category= :category and t.user.id= :userId and t.type = :type")
	public StatisticDTO getCategoryExpensesStatistics(@Param("category") String category, @Param("userId") Long userId, @Param("type") String type);

	@Query("select new com.monthly.expenses.model.DbGraphDTO(t.type, sum(t.creditAmount), sum(t.debitAmount), date_format(t.transactionDate,'%Y')) from Transactions t where t.category= :category and t.user.id= :userId and t.type = :type group by date_format(t.transactionDate,'%Y'), t.type order by date_format(t.transactionDate,'%Y') desc")
	public List<DbGraphDTO> getyearcategorytransactionsCount(@Param("category") String category, @Param("userId") Long userId, @Param("type") String type);

	@Query("select distinct(date_format(t.transactionDate,'%Y')) from Transactions t where t.category= :category and t.user.id= :userId order by date_format(t.transactionDate,'%Y') desc")
	public List<String> getAllCategoryYears(@Param("category") String category, @Param("userId") Long userId);

	
	
	
	
	
	
	
}

package com.monthly.expenses.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.domain.User;
import com.monthly.expenses.model.GraphDTO;
import com.monthly.expenses.model.PieGraphDTO;
import com.monthly.expenses.model.StatisticDTO;
import com.monthly.expenses.service.TransactionService;
import com.monthly.expenses.util.StatisticUtil;

/**
 * The Class StatisticController.
 *
 * @author G Lokesh
 */
@RestController
@RequestMapping("/statistic")
public class StatisticController {
	
	@Autowired
    TransactionService transactionService;

    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getMonthly")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StatisticDTO> getMonthly(@RequestBody User user) {
    	StatisticDTO dateRange = StatisticUtil.getMonthRange();
    	StatisticDTO statistic = transactionService.getStatistics(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
    }
    
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getSelectedMonthly/{monthyear}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StatisticDTO> getMonthly(@PathVariable String monthyear, @RequestBody User user) {
    	Integer year = Integer.parseInt(monthyear.split("-")[0]);
    	Integer month = Integer.parseInt(monthyear.split("-")[1]);
    	StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
    	StatisticDTO statistic = transactionService.getStatistics(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
    }
    
    
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getSelectedMonthlyMinMaxDates/{monthyear}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StatisticDTO> getSelectedMonthlyMinMaxDates(@PathVariable String monthyear, @RequestBody User user) {
    	Integer year = Integer.parseInt(monthyear.split("-")[0]);
    	Integer month = Integer.parseInt(monthyear.split("-")[1]);
    	StatisticDTO statistic = StatisticUtil.getMonthRange(year, month);
        return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
    }
    
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getMonthlyChart")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PieGraphDTO> getMonthlyChart(@RequestBody User user) {
    	StatisticDTO dateRange = StatisticUtil.getMonthRange();
    	PieGraphDTO statistic = transactionService.getMonthlyChart(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<PieGraphDTO>(statistic, HttpStatus.OK);
    }
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getmonthlyransactionsGraph/{monthyear}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<PieGraphDTO> getmonthlyransactionsGraph(@PathVariable String monthyear, @RequestBody User user) {
    	Integer year = Integer.parseInt(monthyear.split("-")[0]);
    	Integer month = Integer.parseInt(monthyear.split("-")[1]);
    	StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
    	PieGraphDTO statistic = transactionService.getMonthlyChart(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<PieGraphDTO>(statistic, HttpStatus.OK);
    }
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getMonthlyTransaction")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GraphDTO> getMonthlyTransaction(@RequestBody User user) {
    	StatisticDTO dateRange = StatisticUtil.getYearRange();
    	GraphDTO statistic = transactionService.getMonthlyransaction(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<GraphDTO>(statistic, HttpStatus.OK);
    }
    
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getMonthlytransactionsCount/{monthyear}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GraphDTO> getMonthlytransactionsCount(@PathVariable String monthyear, @RequestBody User user) {
    	Integer year = Integer.parseInt(monthyear.split("-")[0]);
    	Integer month = Integer.parseInt(monthyear.split("-")[1]);
    	StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
    	GraphDTO statistic = transactionService.getMonthlytransactionsCount(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<GraphDTO>(statistic, HttpStatus.OK);
    }
    
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getYearly")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StatisticDTO> getYearly(@RequestBody User user) {
    	StatisticDTO dateRange = StatisticUtil.getYearRange();
    	StatisticDTO statistic = transactionService.getStatistics(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
    }
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getYearlyTransaction")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<GraphDTO> getYearlyTransaction(@RequestBody User user) {
    	GraphDTO statistic = transactionService.getYearlyTransaction(user.getId());
        return new ResponseEntity<GraphDTO>(statistic, HttpStatus.OK);
    }
    
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getWeekly")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StatisticDTO> getWeekly(@RequestBody User user) {
    	StatisticDTO dateRange = StatisticUtil.getWeeklyRange();
    	StatisticDTO statistic = transactionService.getStatistics(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
    }
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getToday")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StatisticDTO> getToday(@RequestBody User user) {
    	StatisticDTO dateRange = StatisticUtil.getTodayRange();
    	StatisticDTO statistic = transactionService.getStatistics(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
    }
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getTodayTransactions")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Transactions>> getTodayTransactions(@RequestBody User user) {
    	StatisticDTO dateRange = StatisticUtil.getTodayRange();
    	List<Transactions> transactions = transactionService.getTransactions(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
    }
    
    
    /**
     * Products.
     *
     * @return the response entity
     */
    @PostMapping("/getMonthlyTransactions/{monthyear}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Transactions>> getMonthlyTransactions(@PathVariable String monthyear, @RequestBody User user) {
    	Integer year = Integer.parseInt(monthyear.split("-")[0]);
    	Integer month = Integer.parseInt(monthyear.split("-")[1]);
    	StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
    	List<Transactions> transactions = transactionService.getTransactions(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
        return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
    }

}

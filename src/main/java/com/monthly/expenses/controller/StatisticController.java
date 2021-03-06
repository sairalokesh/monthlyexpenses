package com.monthly.expenses.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.domain.User;
import com.monthly.expenses.exception.UnauthorizedException;
import com.monthly.expenses.model.GraphDTO;
import com.monthly.expenses.model.PieGraphDTO;
import com.monthly.expenses.model.StatisticDTO;
import com.monthly.expenses.model.TransactionDTO;
import com.monthly.expenses.service.InvoiceService;
import com.monthly.expenses.service.TransactionService;
import com.monthly.expenses.util.OpenAnyFile;
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

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	ServletContext context;
	
	
	
	/* Start Dashboard Functionality */
	
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getYearly")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getYearly(@RequestBody User user) {
		StatisticDTO dateRange = StatisticUtil.getYearRange();
		StatisticDTO statistic = new StatisticDTO();
		Long count = transactionService.getStatisticsCount(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		if(count!=null && count>0) {
			statistic = transactionService.getStatistics(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		}
		
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMonthly")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getMonthly(@RequestBody User user) {
		StatisticDTO dateRange = StatisticUtil.getMonthRange();
		Long count = transactionService.getStatisticsCount(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		StatisticDTO statistic = new StatisticDTO();
		if(count!=null && count>0) {
			statistic = transactionService.getStatistics(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		}
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
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
		Long count = transactionService.getStatisticsCount(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		StatisticDTO statistic = new StatisticDTO();
		if(count!=null && count>0) {
			statistic = transactionService.getStatistics(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		}

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
		Long count = transactionService.getStatisticsCount(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		StatisticDTO statistic = new StatisticDTO();
		if(count!=null && count>0) {
			statistic = transactionService.getStatistics(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		}
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
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
		GraphDTO statistic = transactionService.getMonthlyransaction(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		return new ResponseEntity<GraphDTO>(statistic, HttpStatus.OK);
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
	@PostMapping("/getMonthlyChart")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PieGraphDTO> getMonthlyChart(@RequestBody User user) {
		StatisticDTO dateRange = StatisticUtil.getMonthRange();
		PieGraphDTO  statistic = transactionService.getMonthlyChart(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		return new ResponseEntity<PieGraphDTO>(statistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getTodayTransactions")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Transactions>> getTodayTransactions(@RequestBody User user) {
		StatisticDTO dateRange = StatisticUtil.getMonthRange();
		List<Transactions> transactions = transactionService.getTransactions(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
	}
	
	/* End Dashboard Functionality */
	
	
	/* Start Transaction Tab Functionality */
	
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMonthlytransactionsCount/{monthyear}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GraphDTO> getMonthlytransactionsCount(@PathVariable String monthyear,@RequestBody User user) {
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
	@PostMapping("/getmonthlyransactionsGraph/{monthyear}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PieGraphDTO> getmonthlyransactionsGraph(@PathVariable String monthyear,@RequestBody User user) {
		Integer year = Integer.parseInt(monthyear.split("-")[0]);
		Integer month = Integer.parseInt(monthyear.split("-")[1]);
		StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
		PieGraphDTO  statistic = transactionService.getMonthlyChart(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		return new ResponseEntity<PieGraphDTO>(statistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMonthlyTransactions/{monthyear}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Transactions>> getMonthlyTransactions(@PathVariable String monthyear,
			@RequestBody User user) {
		Integer year = Integer.parseInt(monthyear.split("-")[0]);
		Integer month = Integer.parseInt(monthyear.split("-")[1]);
		StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
		List<Transactions> transactions = transactionService.getTransactions(dateRange.getStartDate(),dateRange.getEndDate(), user.getId());
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
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
		Long count = transactionService.getStatisticsCount(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		StatisticDTO statistic = new StatisticDTO();
		if(count!=null && count>0) {
			statistic = transactionService.getStatistics(dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		}
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
	}
	
	
	/* End Transaction Tab Functionality */
	
	
	/* Start Categories Tab Functionality */
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getSelectedCategory/{category}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<TransactionDTO>> getSelectedCategory(@PathVariable String category, @RequestBody User user) {
		List<TransactionDTO> transactions = transactionService.getSelectedCategory(category,user.getId());
		return new ResponseEntity<List<TransactionDTO>>(transactions, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMonthcategorytransactionsCount/{category}/{monthyear}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GraphDTO> getMonthcategorytransactionsCount(@PathVariable String category,@PathVariable String monthyear,@RequestBody User user) {
		Integer year = Integer.parseInt(monthyear.split("-")[0]);
		Integer month = Integer.parseInt(monthyear.split("-")[1]);
		StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
		GraphDTO statistic = transactionService.getMonthcategorytransactionsCount(category,dateRange, user.getId());
		return new ResponseEntity<GraphDTO>(statistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getCategoryTransactions/{category}/{monthyear}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Transactions>> getCategoryTransactions(@PathVariable String category,@PathVariable String monthyear,@RequestBody User user) {
		Integer year = Integer.parseInt(monthyear.split("-")[0]);
		Integer month = Integer.parseInt(monthyear.split("-")[1]);
		StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
		List<Transactions> transactions = transactionService.getCategoryTransactions(category,dateRange, user.getId());
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getSelectedCategoryMonthly/{category}/{monthyear}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getSelectedCategoryMonthly(@PathVariable String category,@PathVariable String monthyear,@RequestBody User user) {
		Integer year = Integer.parseInt(monthyear.split("-")[0]);
		Integer month = Integer.parseInt(monthyear.split("-")[1]);
		StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
		StatisticDTO statistic = transactionService.getSelectedCategoryMonthly(category,dateRange.getStartDate(), dateRange.getEndDate(),user.getId());
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
	}
	
	
	/* End Categories Functionality */
	
	/* Start Range Transaction Functionality */
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMobileCurrentMonthlyMinMaxDates")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getMobileCurrentMonthlyMinMaxDates(@RequestBody User user) {
		StatisticDTO statistic = StatisticUtil.getMonthRange();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String dbStartDate = format.format(statistic.getStartDate());
		String dbEndDate = format.format(statistic.getEndDate());
		statistic.setDbStartDate(dbStartDate);
		statistic.setDbEndDate(dbEndDate);  
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMobileSelectedTransactions")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getMobileSelectedTransactions(@RequestBody StatisticDTO statistic) {
		if(StringUtils.hasText(statistic.getDbStartDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = null;
			try {
				String date = statistic.getDbStartDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				startDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setStartDate(startDate);
		}
		
		if(StringUtils.hasText(statistic.getDbEndDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endDate = null;
			try {
				String date = statistic.getDbEndDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				endDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setEndDate(endDate);
		}
		
		Long count = transactionService.getStatisticsCount(statistic.getStartDate(), statistic.getEndDate(),statistic.getUser().getId());
		StatisticDTO dbStatistic = new StatisticDTO();
		if(count!=null && count>0) {
			dbStatistic = transactionService.getStatistics(statistic.getStartDate(), statistic.getEndDate(),statistic.getUser().getId());
		}
		return new ResponseEntity<StatisticDTO>(dbStatistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMobileRangeTransactions")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Transactions>> getMobileRangeTransactions(@RequestBody StatisticDTO statistic) {
		if(StringUtils.hasText(statistic.getDbStartDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = null;
			try {
				String date = statistic.getDbStartDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				startDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setStartDate(startDate);
		}
		
		if(StringUtils.hasText(statistic.getDbEndDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endDate = null;
			try {
				String date = statistic.getDbEndDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				endDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setEndDate(endDate);
		}
		
		List<Transactions> transactions = transactionService.getTransactions(statistic.getStartDate(),statistic.getEndDate(), statistic.getUser().getId());
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMobileRangetransactionsCount")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GraphDTO> getMobileRangetransactionsCount(@RequestBody StatisticDTO statistic) {
		if(StringUtils.hasText(statistic.getDbStartDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = null;
			try {
				String date = statistic.getDbStartDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				startDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setStartDate(startDate);
		}
		
		if(StringUtils.hasText(statistic.getDbEndDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endDate = null;
			try {
				String date = statistic.getDbEndDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				endDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setEndDate(endDate);
		}
		GraphDTO dbstatistic = transactionService.getRangetransactionsCount(statistic.getStartDate(),statistic.getEndDate(), statistic.getUser().getId());
		return new ResponseEntity<GraphDTO>(dbstatistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getMobileRangeTransactionsGraph")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PieGraphDTO> getMobileRangeTransactionsGraph(@RequestBody StatisticDTO statistic) {
		if(StringUtils.hasText(statistic.getDbStartDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date startDate = null;
			try {
				String date = statistic.getDbStartDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				startDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setStartDate(startDate);
		}
		
		if(StringUtils.hasText(statistic.getDbEndDate())) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date endDate = null;
			try {
				String date = statistic.getDbEndDate();
				date = date.replace("T"," ");
				date = date.replace("Z","");
				System.out.println(date);
				
				endDate = format.parse(date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			statistic.setEndDate(endDate);
		}
		
		PieGraphDTO dbstatistic = transactionService.getMonthlyChart(statistic.getStartDate(), statistic.getEndDate(),statistic.getUser().getId());
		return new ResponseEntity<PieGraphDTO>(dbstatistic, HttpStatus.OK);
	}
	
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getCurrentMonthlyMinMaxDates")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getCurrentMonthlyMinMaxDates(@RequestBody User user) {
		StatisticDTO statistic = StatisticUtil.getMonthRange();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
		String dbStartDate = format.format(statistic.getStartDate());
		String dbEndDate = format.format(statistic.getEndDate());
		statistic.setDbStartDate(dbStartDate);
		statistic.setDbEndDate(dbEndDate);
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getSelectedTransactions")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getSelectedTransactions(@RequestBody StatisticDTO statistic) {
		Long count = transactionService.getStatisticsCount(statistic.getStartDate(), statistic.getEndDate(),statistic.getUser().getId());
		StatisticDTO dbStatistic = new StatisticDTO();
		if(count!=null && count>0) {
			dbStatistic = transactionService.getStatistics(statistic.getStartDate(), statistic.getEndDate(),statistic.getUser().getId());
		}
		return new ResponseEntity<StatisticDTO>(dbStatistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getRangeTransactions")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Transactions>> getRangeTransactions(@RequestBody StatisticDTO statistic) {
		List<Transactions> transactions = transactionService.getTransactions(statistic.getStartDate(),statistic.getEndDate(), statistic.getUser().getId());
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getRangetransactionsCount")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GraphDTO> getRangetransactionsCount(@RequestBody StatisticDTO statistic) {
		GraphDTO dbstatistic = transactionService.getRangetransactionsCount(statistic.getStartDate(),statistic.getEndDate(), statistic.getUser().getId());
		return new ResponseEntity<GraphDTO>(dbstatistic, HttpStatus.OK);
	}
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getRangeTransactionsGraph")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<PieGraphDTO> getRangeTransactionsGraph(@RequestBody StatisticDTO statistic) {
		PieGraphDTO dbstatistic = transactionService.getMonthlyChart(statistic.getStartDate(), statistic.getEndDate(),statistic.getUser().getId());
		return new ResponseEntity<PieGraphDTO>(dbstatistic, HttpStatus.OK);
	}
	
	/* End Range Transaction Functionality */
	
	
	
	/* Start Invoice Transaction Functionality */
	
	

	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/getSelectedMonthlyMinMaxDates/{monthyear}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<StatisticDTO> getSelectedMonthlyMinMaxDates(@PathVariable String monthyear,
			@RequestBody User user) {
		Integer year = Integer.parseInt(monthyear.split("-")[0]);
		Integer month = Integer.parseInt(monthyear.split("-")[1]);
		StatisticDTO statistic = StatisticUtil.getMonthRange(year, month);
		statistic.setInvoiceNumber(StatisticUtil.getRandomNumber());
		return new ResponseEntity<StatisticDTO>(statistic, HttpStatus.OK);
	}

	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/generateInvoice/{monthyear}/{invoiceNumber}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Transactions>> generateInvoice(@PathVariable String monthyear, @PathVariable String invoiceNumber, @RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		File mfile =  new File(context.getRealPath("/userprofile/"+File.separator+user.getSignatureName()));
    	if(!mfile.exists()) {
    		throw new UnauthorizedException("Please create user signature in profile creation");
    	}
		Integer year = Integer.parseInt(monthyear.split("-")[0]);
		Integer month = Integer.parseInt(monthyear.split("-")[1]);
		StatisticDTO dateRange = StatisticUtil.getMonthRange(year, month);
		List<Transactions> transactions = transactionService.getTransactions(dateRange.getStartDate(),
				dateRange.getEndDate(), user.getId());
		StatisticDTO dbstatistic = transactionService.getStatistics(dateRange.getStartDate(), dateRange.getEndDate(),
				user.getId());
		if (transactions != null && transactions.size() > 0) {
			boolean status = invoiceService.generateInvoicePdf(context, request, response, transactions, dateRange, dbstatistic, invoiceNumber, user);
			if (status) {
				String fullPath = context.getRealPath("resources/reports/Invoice" + ".pdf");
				OpenAnyFile.openAnyFile(fullPath, context, request, response, "application/pdf", "Invoice.pdf");
			}
		}
		
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);

	}
	
	
	
	/**
	 * Products.
	 *
	 * @return the response entity
	 */
	@PostMapping("/generateRangeInvoice")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Transactions>> generateRangeInvoice(@RequestBody StatisticDTO dateRange, HttpServletRequest request, HttpServletResponse response) {
		File mfile =  new File(context.getRealPath("/userprofile/"+File.separator+dateRange.getUser().getSignatureName()));
    	if(!mfile.exists()) {
    		throw new UnauthorizedException("Please create user signature in profile creation");
    	}
		String invoiceNumber = StatisticUtil.getRandomNumber();
		List<Transactions> transactions = transactionService.getTransactions(dateRange.getStartDate(),dateRange.getEndDate(), dateRange.getUser().getId());
		StatisticDTO dbStatistic = transactionService.getStatistics(dateRange.getStartDate(), dateRange.getEndDate(),dateRange.getUser().getId());
		if (transactions != null && transactions.size() > 0) {
			boolean status = invoiceService.generateInvoicePdf(context, request, response, transactions, dateRange, dbStatistic, invoiceNumber, dateRange.getUser());
			if (status) {
				String fullPath = context.getRealPath("resources/reports/Invoice" + ".pdf");
				OpenAnyFile.openAnyFile(fullPath, context, request, response, "application/pdf", "Invoice.pdf");
			}
		}
		
		return new ResponseEntity<List<Transactions>>(transactions, HttpStatus.OK);

	}
	
	
	
	/* End Invoice Functionality */	
	
}

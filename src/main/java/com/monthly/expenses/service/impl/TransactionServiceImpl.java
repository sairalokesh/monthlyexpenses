package com.monthly.expenses.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.model.DbGraphDTO;
import com.monthly.expenses.model.DbTransactionDTO;
import com.monthly.expenses.model.GraphDTO;
import com.monthly.expenses.model.GraphDataDTO;
import com.monthly.expenses.model.PieGraphDTO;
import com.monthly.expenses.model.StatisticDTO;
import com.monthly.expenses.model.TransactionDTO;
import com.monthly.expenses.repository.TransactionRepository;
import com.monthly.expenses.service.TransactionService;
import com.monthly.expenses.util.StatisticUtil;

/**
 * The Class ProductServiceImpl.
 *
 * @author G Lokesh
 */
@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository transactionRepository;
	
	/* Start Dashboard Functionality */
	
	@Override
	public Long getStatisticsCount(Date startDate, Date endDate, Long userId) {
		return transactionRepository.getStatisticsCount(startDate, endDate, userId);
	}
	
	
	@Override
	public StatisticDTO getStatistics(Date startDate, Date endDate, Long userId) {
		return transactionRepository.getStatistics(startDate, endDate, userId);
	}
	
	@Override
	public GraphDTO getMonthlyransaction(Date startDate, Date endDate, Long userId) {
		Long incomeCount =  transactionRepository.getMonthlyTransactionCount(startDate, endDate, userId,"Income");
		List<DbGraphDTO> dbIncomeGraphDTOs = new ArrayList<DbGraphDTO>();
		if(incomeCount!=null && incomeCount>0) {
			dbIncomeGraphDTOs = transactionRepository.getMonthlyTransaction(startDate, endDate, userId,"Income");
		}
		
		Long expenseCount =  transactionRepository.getMonthlyTransactionCount(startDate, endDate, userId,"Expense");
		List<DbGraphDTO> dbExpenseGraphDTOs = new ArrayList<DbGraphDTO>();
		if(expenseCount!=null && expenseCount>0) {
			dbExpenseGraphDTOs = transactionRepository.getMonthlyTransaction(startDate, endDate, userId,"Expense");
		}
		
		List<String> monthYears = transactionRepository.getAllMonths(startDate, endDate, userId);
		List<String> months = StatisticUtil.getMonths(monthYears);
		GraphDTO graphDTO = getMonthlyTransactions(dbIncomeGraphDTOs, dbExpenseGraphDTOs, months, monthYears);
		return graphDTO;
	}
	
	@Override
	public GraphDTO getYearlyTransaction(Long userId) {
		Long incomeCount =  transactionRepository.getYearlyTransactionCount(userId, "Income");
		List<DbGraphDTO> dbIncomeGraphDTOs = new ArrayList<DbGraphDTO>();
		if(incomeCount!=null && incomeCount>0) {
			dbIncomeGraphDTOs = transactionRepository.getYearlyTransaction(userId, "Income");
		}
		
		Long expenseCount =   transactionRepository.getYearlyTransactionCount(userId, "Expense");
		List<DbGraphDTO> dbExpenseGraphDTOs = new ArrayList<DbGraphDTO>();
		if(expenseCount!=null && expenseCount>0) {
			dbExpenseGraphDTOs = transactionRepository.getYearlyTransaction(userId, "Expense");
		}

		List<String> years = transactionRepository.getAllYears(userId);
		GraphDTO graphDTO = getMonthlyTransactions(dbIncomeGraphDTOs, dbExpenseGraphDTOs, null, years);

		return graphDTO;

	}
	
	@Override
	public PieGraphDTO getMonthlyChart(Date startDate, Date endDate, Long userId) {
		List<DbGraphDTO> dbIncomeGraphDTOs = new ArrayList<DbGraphDTO>();
		List<DbGraphDTO> dbExpensesGraphDTOs = new ArrayList<DbGraphDTO>();
		
		Long incomeCount =  transactionRepository.getMonthlyIncomeChartCount(startDate, endDate, userId, "Income");
		if(incomeCount!=null && incomeCount>0) {
			dbIncomeGraphDTOs = transactionRepository.getMonthlyIncomeChart(startDate, endDate, userId, "Income");
		}
		
		Long expenseCount =  transactionRepository.getMonthlyChartCount(startDate, endDate, userId, "Expense");
		if(expenseCount!=null && expenseCount>0) {
			dbExpensesGraphDTOs = transactionRepository.getMonthlyChart(startDate, endDate, userId, "Expense");
		}
		
		List<DbGraphDTO>  dbGraphDTOs = new ArrayList<DbGraphDTO>();
		if(dbIncomeGraphDTOs!=null && dbIncomeGraphDTOs.size()>0) {
			dbGraphDTOs.addAll(dbIncomeGraphDTOs);
		}
		if(dbExpensesGraphDTOs!=null && dbExpensesGraphDTOs.size()>0) {
			dbGraphDTOs.addAll(dbExpensesGraphDTOs);
		}
		
		PieGraphDTO graphDTO = getMonthlyChart(dbGraphDTOs);
		return graphDTO;
	}
	
	@Override
	public List<Transactions> getTransactions(Date startDate, Date endDate, Long userId) {
		return transactionRepository.getTransactions(startDate, endDate, userId);
	}
	
	/* End Dashboard Functionality */
	
	
	/* Start Transaction Functionality */

	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.TransactionService#findAll()
	 */
	@Override
	public List<TransactionDTO> findAll(Long userId) {
		List<DbTransactionDTO> dbTransactionDTOs = transactionRepository.getAllTransactionsByMontyYear(userId);
		List<TransactionDTO> transactionDTOs = getTransactios(dbTransactionDTOs);
		return transactionDTOs;
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.TransactionService#save(
	 * com.springboot.angular.domain.Transactions)
	 */
	@Override
	public Transactions save(Transactions transactions) {
		return transactionRepository.save(transactions);
	}
	
	@Override
	public GraphDTO getMonthlytransactionsCount(Date startDate, Date endDate, Long userId) {
		Long incomeCount =  transactionRepository.getMonthlytransactionsCountCount(startDate, endDate,userId, "Income");
		List<DbGraphDTO> dbIncomeGraphDTOs = new ArrayList<DbGraphDTO>();
		if(incomeCount!=null && incomeCount>0) {
			dbIncomeGraphDTOs = transactionRepository.getMonthlytransactionsCount(startDate, endDate,userId, "Income");
		}
		
		Long expenseCount =  transactionRepository.getMonthlytransactionsCountCount(startDate, endDate,userId, "Expense");
		List<DbGraphDTO> dbExpenseGraphDTOs = new ArrayList<DbGraphDTO>();
		if(expenseCount!=null && expenseCount>0) {
			dbExpenseGraphDTOs = transactionRepository.getMonthlytransactionsCount(startDate, endDate,userId, "Expense");
		}
		
		List<String> days = transactionRepository.getAllDays(startDate, endDate, userId);
		GraphDTO graphDTO = getMonthlyTransactions(dbIncomeGraphDTOs, dbExpenseGraphDTOs, null, days);
		return graphDTO;
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.TransactionService#delete(java
	 * .lang.Long)
	 */
	@Override
	public void delete(Long id) {
		transactionRepository.delete(id);
	}
	
	@Override
	public Transactions findById(Long transactionId) {
		return transactionRepository.findOne(transactionId);
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see com.springboot.angular.service.impl.TransactionService#update(
	 * com.springboot.angular.domain.Transactions)
	 */
	@Override
	public Transactions update(Transactions transactions) {
		Transactions dbTransactions = null;
		dbTransactions = transactionRepository.findOne(transactions.getId());
		if (dbTransactions == null) {
			throw new UsernameNotFoundException("Invalid Transaction");
		}

		transactions.setCreatedDate(dbTransactions.getCreatedDate());
		transactions.setCreatedBy(dbTransactions.getCreatedBy());
		dbTransactions = transactionRepository.save(transactions);
		return dbTransactions;
	}
	
	/* End Transaction Functionality */
	
	/* Start Categories Functionality */
	
	@Override
	public List<TransactionDTO> getSelectedCategory(String category, Long userId) {
		List<DbTransactionDTO> dbTransactionDTOs = new ArrayList<DbTransactionDTO>();
		String type = transactionRepository.getCategoryType(category);
		if (StringUtils.hasText(type)) {
			if (type.equalsIgnoreCase("Income")) {
				Long incomecount = transactionRepository.getCategoryStatisticsCount(category, userId, "Income");
				if(incomecount!=null && incomecount>0) {
					dbTransactionDTOs = transactionRepository.getAllIncomeTransactionsByCategory(category, userId, "Income");
				}
			} else if (type.equalsIgnoreCase("Expense")) {
				Long expensecount = transactionRepository.getCategoryStatisticsCount(category, userId, "Expense");
				if(expensecount!=null && expensecount>0) {
					dbTransactionDTOs = transactionRepository.getAllExpensesTransactionsByCategory(category, userId, "Expense");
				}
			}
		}
		
		List<TransactionDTO> transactionDTOs = getTransactios(dbTransactionDTOs);
		return transactionDTOs;
	}
	
	@Override
	public GraphDTO getMonthcategorytransactionsCount(String category, StatisticDTO dateRange, Long userId) {
		List<DbGraphDTO> dbIncomeGraphDTOs = new ArrayList<DbGraphDTO>();
		List<DbGraphDTO> dbExpenseGraphDTOs = new ArrayList<DbGraphDTO>();
		String type = transactionRepository.getCategoryType(category);
		if (StringUtils.hasText(type)) {
			if (type.equalsIgnoreCase("Income")) {
				Long incomecount = transactionRepository.getcategoryRangetransactionsCountCount(category,dateRange.getStartDate(),dateRange.getEndDate(), userId, "Income");
				if(incomecount!=null && incomecount>0) {
					dbIncomeGraphDTOs = transactionRepository.getcategoryRangetransactionsCount(category,dateRange.getStartDate(),dateRange.getEndDate(), userId, "Income");
				}
			} else if (type.equalsIgnoreCase("Expense")) {
				Long expensecount = transactionRepository.getcategoryRangetransactionsCountCount(category,dateRange.getStartDate(),dateRange.getEndDate(), userId, "Expense");
				if(expensecount!=null && expensecount>0) {
					dbExpenseGraphDTOs = transactionRepository.getcategoryRangetransactionsCount(category,dateRange.getStartDate(),dateRange.getEndDate(), userId, "Expense");
				}
			}
		}

		List<String> days = transactionRepository.getAllCategoryAllDays(category,dateRange.getStartDate(),dateRange.getEndDate(), userId);
		GraphDTO graphDTO = getMonthlyTransactions(dbIncomeGraphDTOs, dbExpenseGraphDTOs, null, days);
		return graphDTO;
	}
	
	@Override
	public List<Transactions> getCategoryTransactions(String category,StatisticDTO dateRange, Long userId) {
		List<Transactions> transactions = new ArrayList<Transactions>();
		String type = transactionRepository.getCategoryType(category);
		if (StringUtils.hasText(type)) {
			if (type.equalsIgnoreCase("Income")) {
				transactions = transactionRepository.getCategoryIncomeTransactions(category, userId, dateRange.getStartDate(),dateRange.getEndDate(), "Income");
			} else if (type.equalsIgnoreCase("Expense")) {
				transactions = transactionRepository.getCategoryExpensesTransactions(category, userId, dateRange.getStartDate(),dateRange.getEndDate(), "Expense");
			}
		}

		return transactions;
	}
	
	@Override
	public StatisticDTO getSelectedCategoryMonthly(String category, Date startDate, Date endDate, Long userId) {
		StatisticDTO statisticDTO = new StatisticDTO();
		String type = transactionRepository.getCategoryType(category);
		if (StringUtils.hasText(type)) {
			if (type.equalsIgnoreCase("Income")) {
				Long incomecount = transactionRepository.getcategoryRangetransactionsCountCount(category,startDate,endDate, userId, "Income");
				if(incomecount!=null && incomecount>0) {
					statisticDTO = transactionRepository.getSelectedIncomeCategoryMonthly(category,startDate,endDate, userId, "Income");
				}
			} else if (type.equalsIgnoreCase("Expense")) {
				Long expensecount = transactionRepository.getcategoryRangetransactionsCountCount(category,startDate,endDate, userId, "Expense");
				if(expensecount!=null && expensecount>0) {
					statisticDTO = transactionRepository.getSelectedExpensesCategoryMonthly(category,startDate,endDate, userId, "Expense");
				}
			}
		}

		return statisticDTO;
	}
	
	/* End Categories Functionality */
	
	/* Start Range Transactions Functionality */
	
	@Override
	public GraphDTO getRangetransactionsCount(Date startDate, Date endDate, Long userId) {
		Long incomeCount =  transactionRepository.getRangetransactionsCountCount(startDate, endDate, userId,"Income");
		List<DbGraphDTO> dbIncomeGraphDTOs = new ArrayList<DbGraphDTO>();
		if(incomeCount!=null && incomeCount>0) {
			dbIncomeGraphDTOs = transactionRepository.getRangetransactionsCount(startDate, endDate, userId,"Income");
		}
		
		Long expenseCount =  transactionRepository.getRangetransactionsCountCount(startDate, endDate,userId, "Expense");
		List<DbGraphDTO> dbExpenseGraphDTOs = new ArrayList<DbGraphDTO>();
		if(expenseCount!=null && expenseCount>0) {
			dbExpenseGraphDTOs = transactionRepository.getRangetransactionsCount(startDate, endDate,userId, "Expense");
		}

		List<String> monthyears = transactionRepository.getAllYearsAndMonths(startDate, endDate, userId);
		GraphDTO graphDTO = getMonthlyTransactions(dbIncomeGraphDTOs, dbExpenseGraphDTOs, null, monthyears);
		return graphDTO;
	}
	
	/* End Range Transaction Functionality */

	
	/* Private Method inside the Service Class */
	private GraphDTO getMonthlyTransactions(List<DbGraphDTO> dbIncomeGraphDTOs, List<DbGraphDTO> dbExpenseGraphDTOs,
			List<String> months, List<String> monthYears) {
			GraphDTO graphDTO = new GraphDTO();
			if (months != null && months.size() > 0) {
				graphDTO.setMonthYear(months);
			} else {
				graphDTO.setMonthYear(monthYears);
			}

			List<GraphDataDTO> graphDataDTOs = new ArrayList<GraphDataDTO>();
			Map<String, List<Double>> map = new LinkedHashMap<String, List<Double>>();

			if (monthYears != null && monthYears.size() > 0 && dbIncomeGraphDTOs != null && dbIncomeGraphDTOs.size() > 0) {
				for (String monthyear : monthYears) {
					boolean flag = true;
					for (DbGraphDTO income : dbIncomeGraphDTOs) {
						if (monthyear.equalsIgnoreCase(income.getMonthYear())) {
							flag = false;
							if (map.containsKey(income.getCategory())) {
								List<Double> incomes = map.get(income.getCategory());
								incomes.add(income.getIncome());
								map.put(income.getCategory(), incomes);

							} else {
								List<Double> incomes = new ArrayList<Double>();
								incomes.add(income.getIncome());
								map.put(income.getCategory(), incomes);
							}
						}
					}
					if (flag) {
						if (map.containsKey("Income")) {
							List<Double> incomes = map.get("Income");
							incomes.add(0.00);
							map.put("Income", incomes);

						} else {
							List<Double> incomes = new ArrayList<Double>();
							incomes.add(0.00);
							map.put("Income", incomes);
						}

					}

				}
			}

			if (monthYears != null && monthYears.size() > 0 && dbExpenseGraphDTOs != null
					&& dbExpenseGraphDTOs.size() > 0) {
				for (String monthyear : monthYears) {
					boolean flag = true;
					for (DbGraphDTO expense : dbExpenseGraphDTOs) {
						if (monthyear.equalsIgnoreCase(expense.getMonthYear())) {
							flag = false;
							if (map.containsKey(expense.getCategory())) {
								List<Double> expenses = map.get(expense.getCategory());
								expenses.add(expense.getExpenses());
								map.put(expense.getCategory(), expenses);

							} else {
								List<Double> expenses = new ArrayList<Double>();
								expenses.add(expense.getExpenses());
								map.put(expense.getCategory(), expenses);
							}
						}
					}
					if (flag) {
						if (map.containsKey("Expense")) {
							List<Double> expenses = map.get("Expense");
							expenses.add(0.00);
							map.put("Expense", expenses);

						} else {
							List<Double> expenses = new ArrayList<Double>();
							expenses.add(0.00);
							map.put("Expense", expenses);
						}

					}

				}
			}

			if (map != null && map.size() > 0) {
				for (Map.Entry<String, List<Double>> entry : map.entrySet()) {
					String backgroundColor = "";
					String borderColor = "";
					if (entry.getKey().equalsIgnoreCase("Income")) {
						backgroundColor = "#2a91d6";
						borderColor = "#2a91d6";
					} else {
						backgroundColor = "#f66183";
						borderColor = "#f66183";
					}
					GraphDataDTO graphDataDTO = new GraphDataDTO(entry.getValue(), entry.getKey(), false, backgroundColor,
							borderColor);
					graphDataDTOs.add(graphDataDTO);
				}

			}
			graphDTO.setDataDTO(graphDataDTOs);
			return graphDTO;
		}
	
	
	private PieGraphDTO getMonthlyChart(List<DbGraphDTO> dbGraphDTOs) {
		List<String> labels = new ArrayList<String>();
		List<Double> datas = new ArrayList<Double>();
		List<String> colors = new ArrayList<String>();
		if (dbGraphDTOs != null && dbGraphDTOs.size() > 0) {
			for (DbGraphDTO graphDTO : dbGraphDTOs) {
				labels.add(graphDTO.getCategory());
				datas.add(graphDTO.getExpenses());
				colors.add(StatisticUtil.getRandomColor());

			}
		}

		PieGraphDTO dto = new PieGraphDTO();
		dto.setData(datas);
		dto.setLabels(labels);
		dto.setColor(colors);
		return dto;
	}
	
	private List<TransactionDTO> getTransactios(List<DbTransactionDTO> dbTransactionDTOs) {
		List<TransactionDTO> transactionDTOs = new ArrayList<TransactionDTO>();
		if (dbTransactionDTOs != null && dbTransactionDTOs.size() > 0) {
			Map<String, List<DbTransactionDTO>> map = new LinkedHashMap<String, List<DbTransactionDTO>>();
			for (DbTransactionDTO dbTransactionDTO : dbTransactionDTOs) {
				String year = dbTransactionDTO.getMonthYear().split("-")[0];
				if (map.containsKey(year)) {
					List<DbTransactionDTO> dtos = map.get(year);
					dtos.add(dbTransactionDTO);
					map.put(year, dtos);
				} else {
					List<DbTransactionDTO> dtos = new ArrayList<DbTransactionDTO>();
					dtos.add(dbTransactionDTO);
					map.put(year, dtos);
				}

			}

			for (Map.Entry<String, List<DbTransactionDTO>> entry : map.entrySet()) {
				TransactionDTO transactionDTO = new TransactionDTO();
				transactionDTO.setYear(entry.getKey());
				transactionDTO.setTransactions(entry.getValue());
				transactionDTOs.add(transactionDTO);
			}

		}
		return transactionDTOs;
	}


	

}

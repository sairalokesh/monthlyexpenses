package com.monthly.expenses.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.monthly.expenses.domain.Transactions;
import com.monthly.expenses.model.StatisticDTO;

public interface InvoiceService {

	boolean generateInvoicePdf(ServletContext context, HttpServletRequest request, HttpServletResponse response,
			List<Transactions> transactions, StatisticDTO dateRange, StatisticDTO dbstatistic,String invoiceNumber);

}

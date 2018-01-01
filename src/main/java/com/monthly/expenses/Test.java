package com.monthly.expenses;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.monthly.expenses.model.StatisticDTO;

public class Test {
	public static StatisticDTO getMonthRange(Integer year, Integer month) {
		StatisticDTO statisticDTO = new StatisticDTO();
		Date startDate, endDate;

		{
			Calendar calendar = getCalendarForNow();
			calendar.set(Calendar.MONTH, month-1);
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			setTimeToBeginningOfDay(calendar);
			startDate = calendar.getTime();
			System.out.println(startDate);
			statisticDTO.setStartDate(startDate);

		}

		{
			Calendar calendar = getCalendarForNow();
			calendar.set(Calendar.MONTH, month-1);
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			setTimeToEndofDay(calendar);
			endDate = calendar.getTime();
			System.out.println(endDate);
			statisticDTO.setEndDate(endDate);
		}

		return statisticDTO;
	}
	
	
	private static Calendar getCalendarForNow() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}

	private static void setTimeToBeginningOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	private static void setTimeToEndofDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
	}
	

	public static void main(String[] args) {
		Test.getMonthRange(2018, 1);
	}

}

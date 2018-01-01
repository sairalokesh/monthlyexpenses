package com.monthly.expenses.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.monthly.expenses.model.StatisticDTO;

public class StatisticUtil {

	public static StatisticDTO getMonthRange() {
		StatisticDTO statisticDTO = new StatisticDTO();
		Date startDate, endDate;

		{
			Calendar calendar = getCalendarForNow();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			setTimeToBeginningOfDay(calendar);
			startDate = calendar.getTime();
			statisticDTO.setStartDate(startDate);

		}

		{
			Calendar calendar = getCalendarForNow();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			setTimeToEndofDay(calendar);
			endDate = calendar.getTime();
			statisticDTO.setEndDate(endDate);
		}

		return statisticDTO;
	}

	public static StatisticDTO getYearRange() {
		StatisticDTO statisticDTO = new StatisticDTO();
		Date startDate, endDate;

		{
			Calendar calendar = getCalendarForNow();
			calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
			setTimeToBeginningOfDay(calendar);
			startDate = calendar.getTime();
			statisticDTO.setStartDate(startDate);

		}

		{
			Calendar calendar = getCalendarForNow();
			calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
			setTimeToEndofDay(calendar);
			endDate = calendar.getTime();
			statisticDTO.setEndDate(endDate);
		}

		return statisticDTO;
	}

	public static StatisticDTO getWeeklyRange() {
		StatisticDTO statisticDTO = new StatisticDTO();
		Date startDate, endDate;

		{
			Calendar calendar = getCalendarForNow();
			calendar.add(Calendar.DATE, -7);
			setTimeToBeginningOfDay(calendar);
			startDate = calendar.getTime();
			statisticDTO.setStartDate(startDate);

		}

		{
			Calendar calendar = getCalendarForNow();
			setTimeToEndofDay(calendar);
			endDate = calendar.getTime();
			statisticDTO.setEndDate(endDate);
		}

		return statisticDTO;
	}

	public static StatisticDTO getTodayRange() {
		StatisticDTO statisticDTO = new StatisticDTO();
		Date startDate, endDate;

		{
			Calendar calendar = getCalendarForNow();
			setTimeToBeginningOfDay(calendar);
			startDate = calendar.getTime();
			statisticDTO.setStartDate(startDate);

		}

		{
			Calendar calendar = getCalendarForNow();
			setTimeToEndofDay(calendar);
			endDate = calendar.getTime();
			statisticDTO.setEndDate(endDate);
		}

		return statisticDTO;
	}

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
			statisticDTO.setStartDate(startDate);

		}

		{
			Calendar calendar = getCalendarForNow();
			calendar.set(Calendar.MONTH, month-1);
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			setTimeToEndofDay(calendar);
			endDate = calendar.getTime();
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

	public static List<String> getMonths(List<String> monthYears) {
		List<String> months = new ArrayList<String>();
		if (monthYears != null && monthYears.size() > 0) {
			for (String month : monthYears) {
				if (month.equalsIgnoreCase("12"))
					months.add("December");
				if (month.equalsIgnoreCase("11"))
					months.add("November");
				if (month.equalsIgnoreCase("10"))
					months.add("October");
				if (month.equalsIgnoreCase("09"))
					months.add("September");
				if (month.equalsIgnoreCase("08"))
					months.add("August");
				if (month.equalsIgnoreCase("07"))
					months.add("July");
				if (month.equalsIgnoreCase("06"))
					months.add("June");
				if (month.equalsIgnoreCase("05"))
					months.add("May");
				if (month.equalsIgnoreCase("04"))
					months.add("April");
				if (month.equalsIgnoreCase("03"))
					months.add("March");
				if (month.equalsIgnoreCase("02"))
					months.add("Feburary");
				if (month.equalsIgnoreCase("01"))
					months.add("January");
			}

		}
		return months;
	}

	public static String getRandomColor() {
		String[] letters = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
		String color = "#";
		for (int i = 0; i < 6; i++) {
			color += letters[(int) Math.round(Math.random() * 15)];
		}
		return color;
	}

}

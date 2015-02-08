/**
 * DateUtils.java
 */
package com.saituo.order.commons.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateBetweenUtils {
	public static Calendar getCalendar(final Date date) {
		return DateUtils.getCalendar(date);
	}

	private static int getMonthMinus(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);
	}

	public static int getMonthsAbs(final Date date1, final Date date2) {
		return Math.abs(getMonths(date1, date2));
	}

	private static int getMonths(final Date date1, final Date date2) {
		return getMonths(getCalendar(date1), getCalendar(date2));
	}

	private static int getMonthsTruncDay(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return getMonthMinus(objCalendarDate1, objCalendarDate2)
				- (monthDayAfter(objCalendarDate1, objCalendarDate2) ? 1 : 0);
	}

	public static Date getFirstDayAfterNextMonth(final Date date, final int amount) {
		final Calendar calendar = getCalendar(date);
		calendar.add(Calendar.MONTH, amount);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
	public static Date addMonth(final Date date, final int amount) {
		final Calendar calendar = getCalendar(date);
		calendar.add(Calendar.MONTH, amount);
		return calendar.getTime();
	}
	private static int getMonths(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return getYears(objCalendarDate1, objCalendarDate2) * 12 + getMonthMinus(objCalendarDate1, objCalendarDate2);
	}

	public static int getMonthsTruncDay(final Date date1, final Date date2) {
		final Calendar objCalendarDate1 = getCalendar(date1);
		final Calendar objCalendarDate2 = getCalendar(date2);

		if (objCalendarDate2.equals(objCalendarDate1)) {
			return 0;
		}
		Calendar paramNew = null;
		Calendar paramOld = null;

		if (objCalendarDate1.after(objCalendarDate2)) {
			paramNew = objCalendarDate1;
			paramOld = objCalendarDate2;
		} else {
			paramNew = objCalendarDate2;
			paramOld = objCalendarDate1;
		}
		return getMonths(paramOld, paramNew);

	}

	public static int getDays(final Date date1, final Date date2) {
		final Calendar objCalendarDate1 = getCalendar(date1);
		final Calendar objCalendarDate2 = getCalendar(date2);

		if (objCalendarDate2.equals(objCalendarDate1)) {
			return 0;
		}
		Calendar paramNew = null;
		Calendar paramOld = null;

		if (objCalendarDate1.after(objCalendarDate2)) {
			paramNew = objCalendarDate1;
			paramOld = objCalendarDate2;
		} else {
			paramNew = objCalendarDate2;
			paramOld = objCalendarDate1;
		}

		return getDays(paramOld, paramNew);

	}

	private static int getDays(final Calendar paramOld, final Calendar paramNew) {
		int days = paramNew.get(Calendar.DAY_OF_YEAR) - paramOld.get(Calendar.DAY_OF_YEAR);
		final Calendar tempDate = (Calendar) paramOld.clone();
		final int inc = 1;
		for (int i = 0, years = paramNew.get(Calendar.YEAR) - paramOld.get(Calendar.YEAR); i < years; i += inc) {
			days += tempDate.getActualMaximum(Calendar.DAY_OF_YEAR);
			tempDate.add(Calendar.YEAR, inc);
		}
		return days;
	}

	private static int getYears(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR);
	}

	private static boolean monthDayAfter(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH);
	}

	private DateBetweenUtils() {

	}
}
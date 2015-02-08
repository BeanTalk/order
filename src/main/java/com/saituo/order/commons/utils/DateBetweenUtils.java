/**
 * DateUtils.java
 */
package com.saituo.order.commons.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>Module: 日期间隔工具</p>
 * <p>Description: 日期间隔工具 </p>
 * <p>Date: 2010-10-30</p>
 *
 * @author yintj@neusoft.com
 * @version 1.0
 *
 * <p> 修改历史</p>
 * <p> 序号 日期 修改人 修改原因</p>
 *
 */
public final class DateBetweenUtils {
	/**
	 * 获取Calendar类型
	 * @param date <code>java.util.Date<code>类型实例
	 * @return  <code>java.util.Calendar<code>类型实例
	 */
	public static Calendar getCalendar(final Date date) {
		return DateUtils.getCalendar(date);
	}

	/**
	 * 标准月份差
	 * @param objCalendarDate1
	 * @param objCalendarDate2
	 * @return 差值
	 */
	private static int getMonthMinus(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);
	}

	/**
	 * 获取月份差值绝对值
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthsAbs(final Date date1, final Date date2) {
		return Math.abs(getMonths(date1, date2));
	}

	/**
	 * 获取月份差值
	 * @param date1
	 * @param date2
	 * @return
	 */
	private static int getMonths(final Date date1, final Date date2) {
		return getMonths(getCalendar(date1), getCalendar(date2));
	}

	/**
	 * 获取月份差，并根据天数截断
	 * @param objCalendarDate1
	 * @param objCalendarDate2
	 * @return 差
	 */
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
	/**
	 * 获取两日期间年份差的绝对值
	 * @param objCalendarDate1 较大值
	 * @param objCalendarDate2 较小
	 * @return 月份差，不足1月按0计算
	 */
	private static int getMonths(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return getYears(objCalendarDate1, objCalendarDate2) * 12 + getMonthMinus(objCalendarDate1, objCalendarDate2);
	}

	/**
	 * 获取两日期间月份差的绝对值
	 * @param date1
	 * @param date2
	 * @return 月份差，不足1月按0计算
	 */
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

	/**
	 * 获取天数差的绝对值
	 * @param date1
	 * @param date2
	 * @return 月份差，不足1月按0计算
	 */
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

	/**
	 * 获取天数差
	 * @param paramOld
	 * @param paramNew
	 * @return 天数差
	 */
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

	/**
	 * 获取两日期间年份差的绝对值
	 * @param objCalendarDate1
	 * @param objCalendarDate2
	 * @return 年份差的<code>int</code>数值
	 */
	private static int getYears(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR);
	}


	/**
	 * 判断日期的Day是否比第二个日期的Day大
	 * @param objCalendarDate1
	 * @param objCalendarDate2
	 * @return
	 */
	private static boolean monthDayAfter(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH);
	}

	private DateBetweenUtils() {

	}
}
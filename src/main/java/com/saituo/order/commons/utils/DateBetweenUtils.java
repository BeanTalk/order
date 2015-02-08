/**
 * DateUtils.java
 */
package com.saituo.order.commons.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>Module: ���ڼ������</p>
 * <p>Description: ���ڼ������ </p>
 * <p>Date: 2010-10-30</p>
 *
 * @author yintj@neusoft.com
 * @version 1.0
 *
 * <p> �޸���ʷ</p>
 * <p> ��� ���� �޸��� �޸�ԭ��</p>
 *
 */
public final class DateBetweenUtils {
	/**
	 * ��ȡCalendar����
	 * @param date <code>java.util.Date<code>����ʵ��
	 * @return  <code>java.util.Calendar<code>����ʵ��
	 */
	public static Calendar getCalendar(final Date date) {
		return DateUtils.getCalendar(date);
	}

	/**
	 * ��׼�·ݲ�
	 * @param objCalendarDate1
	 * @param objCalendarDate2
	 * @return ��ֵ
	 */
	private static int getMonthMinus(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH);
	}

	/**
	 * ��ȡ�·ݲ�ֵ����ֵ
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthsAbs(final Date date1, final Date date2) {
		return Math.abs(getMonths(date1, date2));
	}

	/**
	 * ��ȡ�·ݲ�ֵ
	 * @param date1
	 * @param date2
	 * @return
	 */
	private static int getMonths(final Date date1, final Date date2) {
		return getMonths(getCalendar(date1), getCalendar(date2));
	}

	/**
	 * ��ȡ�·ݲ�����������ض�
	 * @param objCalendarDate1
	 * @param objCalendarDate2
	 * @return ��
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
	 * ��ȡ�����ڼ���ݲ�ľ���ֵ
	 * @param objCalendarDate1 �ϴ�ֵ
	 * @param objCalendarDate2 ��С
	 * @return �·ݲ����1�°�0����
	 */
	private static int getMonths(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return getYears(objCalendarDate1, objCalendarDate2) * 12 + getMonthMinus(objCalendarDate1, objCalendarDate2);
	}

	/**
	 * ��ȡ�����ڼ��·ݲ�ľ���ֵ
	 * @param date1
	 * @param date2
	 * @return �·ݲ����1�°�0����
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
	 * ��ȡ������ľ���ֵ
	 * @param date1
	 * @param date2
	 * @return �·ݲ����1�°�0����
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
	 * ��ȡ������
	 * @param paramOld
	 * @param paramNew
	 * @return ������
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
	 * ��ȡ�����ڼ���ݲ�ľ���ֵ
	 * @param objCalendarDate1
	 * @param objCalendarDate2
	 * @return ��ݲ��<code>int</code>��ֵ
	 */
	private static int getYears(final Calendar objCalendarDate1, final Calendar objCalendarDate2) {
		return objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR);
	}


	/**
	 * �ж����ڵ�Day�Ƿ�ȵڶ������ڵ�Day��
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
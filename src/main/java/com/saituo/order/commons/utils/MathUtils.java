package com.saituo.order.commons.utils;

import java.text.DecimalFormat;

public class MathUtils {

	public static double getMathDefault(Double dat) {
		if (dat == null) {
			return (double) 1;
		} else {
			return dat;
		}
	}

	public static double getDoublePoint(double dat) {
		DecimalFormat df = new DecimalFormat("##.00");
		return Double.parseDouble(df.format(dat));
	}

}

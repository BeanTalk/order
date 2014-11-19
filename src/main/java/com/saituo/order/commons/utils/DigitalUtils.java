package com.saituo.order.commons.utils;

import java.math.BigDecimal;

public class DigitalUtils {

	public static double[] round2(double[] doubleRes) {
		double[] result = new double[doubleRes.length];
		for (int i = 0; i < doubleRes.length; i++) {
			BigDecimal bg = new BigDecimal(doubleRes[i]);
			result[i] = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return result;
	}

	public static double round4(double doubleRes) {
		BigDecimal bg = new BigDecimal(doubleRes);
		return bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}

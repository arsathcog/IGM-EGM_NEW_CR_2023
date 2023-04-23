package com.rclgroup.dolphin.web.igm.jsonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String GenrateJobAndSerialNumber() {

		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		long xxx = (long) Math.floor(Math.random() * 10000);
		String tracking = simpleDateFormat.format(new Date()) + xxx;

		return tracking;
	}

	public static String JobDate() {
		return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
	}
	
	public static String emptyCheck(String jsonVal) {
		
		if ( null == jsonVal  && jsonVal.equals("") ) {
			return null;
		}
		return jsonVal;
		
		
	}
}

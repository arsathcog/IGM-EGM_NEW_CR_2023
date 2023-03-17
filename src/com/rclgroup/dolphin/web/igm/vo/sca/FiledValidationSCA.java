package com.rclgroup.dolphin.web.igm.vo.sca;


public class FiledValidationSCA {
	public static String isNullAndSetlength(String argString, int l) {
		if (argString==null)
			return "";
		else {
			if (argString.length() > l)
				return argString.substring(0, l);
			else
				return argString;
		}
	}
}
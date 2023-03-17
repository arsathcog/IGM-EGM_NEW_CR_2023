package com.rclgroup.dolphin.web.igm.vo.scc;


public class FiledValidationSCC {
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
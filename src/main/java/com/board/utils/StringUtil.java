package com.board.utils;

import org.springframework.util.StringUtils;

public class StringUtil {

	public static boolean isEmpty(Object str) {
		return StringUtils.isEmpty(str);
	}
	
	public static boolean isNotEmpty(Object str) {
		return !isEmpty(str);
	}

}

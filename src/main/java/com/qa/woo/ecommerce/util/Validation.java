package com.qa.woo.ecommerce.util;

public class Validation {
	
	public static boolean validInt(int id) {
		boolean valid = false;
		if (id > 0) {
			valid = true;
		}
		return valid;
	}

}

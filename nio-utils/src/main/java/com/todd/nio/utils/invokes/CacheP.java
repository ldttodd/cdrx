package com.todd.nio.utils.invokes;


public class CacheP {
	public static Object invokes(final Object service) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String name = service.getClass().getCanonicalName();
		if (name.indexOf("$") != -1) {
			name = name.substring(0, name.indexOf("$"));
		}
		if (name.indexOf("@") != -1) {
			name = name.substring(0, name.indexOf("@"));
		}
		return Class.forName(name).newInstance();
	}
}

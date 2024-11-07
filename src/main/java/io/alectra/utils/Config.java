package io.alectra.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Config {
	public enum Key {
	BROWSER, ENDPOINT, USERNAME, PASSWORD, URL, WAIT_TIMEOUT;
	}
	
	 private static Properties properties = new Properties();

	    static {
	        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config\\config.properties")) {
	            if (input == null) {
	                throw new RuntimeException("Unable to find config.properties");
	            }
	            properties.load(input);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	    }

	    public static String get(Key key) {
	        return properties.getProperty(key.name());
	    }
	    public static List<String> getUsernames() {
	        return Arrays.asList(get(Key.USERNAME).split(","));
	    }
	    public static long getWaitTimeout() {
	        return Long.parseLong(get(Key.WAIT_TIMEOUT)); // Assuming you have added WAIT_TIMEOUT to your Key enum
	    }

	    
}

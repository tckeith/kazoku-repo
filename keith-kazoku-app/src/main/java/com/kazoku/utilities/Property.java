package com.kazoku.utilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Property {

	public static String propertiesPath;

	public String getPropertiesPath() {
		return propertiesPath;
	}

	public void setPropertiesPath(String propPath) {
		propertiesPath = propPath;
	}

	private Property() {}
	
	public static String get(String key){
    	try{
    		
    		InputStream is = new FileInputStream(propertiesPath);
    		
    		Properties p = new Properties();
    		
    		p.load(is);
    		
    		return p.getProperty(key);
    		
    	}catch(Exception e){
    		return null;
    	}
    }

}

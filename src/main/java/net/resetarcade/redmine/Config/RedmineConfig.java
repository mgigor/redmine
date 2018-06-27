package net.resetarcade.redmine.Config;

import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;

public class RedmineConfig {

	private final static String URI = "http://escoladeti.unicesumar.edu.br:8082/";
	private final static String API_ACCESS_KEY = "047310c2ef2d941da6b192c5080a1edb9ab5fb14";
	private final static RedmineManager REDMINE = RedmineManagerFactory.createWithApiKey(URI, API_ACCESS_KEY);;
		
	public static RedmineManager getRedmineManager() {
		return REDMINE;
	}

	public static String getUri() {
		return URI;
	}

	public static String getApiAccessKey() {
		return API_ACCESS_KEY;
	}	
}

package com.igt.analyser;

public class BPMNParserImpl implements BPMNParser {

	@Override
	public String[] getActivities(String filepath) {
		return new String[] { "Get all users", "Get the current wheather data", "Create an admin user"}; 
	}

}

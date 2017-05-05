package com.igt.analyser;

public interface Analyser {
	
	/**
	 * Finds matching services for the given BPMN activities based on their names. 
	 * @param activityNames
	 * @param serviceNames
	 * @return
	 */
	BPMNActivity[] getRecommendedServices(String[] activityNames, String[] serviceNames);
}

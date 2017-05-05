package com.igt.analyser;

import java.util.ArrayList;
import java.util.List;

public class AnalyserImpl implements Analyser {

	private static final String NAME_SPLIT_REGEX = "[A-Z\\-]";
	
	@Override
	public BPMNActivity[] getRecommendedServices(String[] activityNames, String[] serviceNames) {
		List<BPMNActivity> activities = new ArrayList<BPMNActivity>();
		
		for (String activityName : activityNames) {
			List<WSDLServiceRecommendation> recommendations = new ArrayList<WSDLServiceRecommendation>();
			
			for (String serviceName : serviceNames) {
				String[] activitySplit = activityName.split(NAME_SPLIT_REGEX);
				String[] serviceSplit = serviceName.split(NAME_SPLIT_REGEX);
				
				WSDLServiceRecommendation recommandation = compareNames(activitySplit, serviceSplit);
				recommandation.serviceName = serviceName;
				
				if (recommandation.precision > 0.25) {
					recommendations.add(recommandation);
				}
			}
			
			BPMNActivity activity = new BPMNActivity();
			activity.name = activityName;
			activity.recommendations = recommendations;
		}
		
		return activities.toArray(new BPMNActivity[activities.size()]);
	}
	
	private WSDLServiceRecommendation compareNames(String[] activityNameSplit, String[] serviceNameSplit) {
		
		WSDLServiceRecommendation recommendation = new WSDLServiceRecommendation();
		
		for (String actName : activityNameSplit) {
			for (String servName : serviceNameSplit) {
				if (actName.toUpperCase().equals(servName.toUpperCase())) {
					recommendation.matches++;
				}
			}
		}
		
		recommendation.precision = (float)recommendation.matches / serviceNameSplit.length;
		// TODO: recall
		
		return recommendation;
	}
}

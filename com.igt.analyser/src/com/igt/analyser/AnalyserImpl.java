package com.igt.analyser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnalyserImpl implements Analyser {

	@Override
	public BPMNActivity[] getRecommendedServices(String[] activityNames, String[] serviceNames) {
		List<BPMNActivity> activities = new ArrayList<BPMNActivity>();

		for (String activityName : activityNames) {
			List<WSDLServiceRecommendation> recommendations = new ArrayList<WSDLServiceRecommendation>();

			for (String serviceName : serviceNames) {
				double similarity = similarity(activityName, serviceName);

				if (similarity > 0.25) {
					WSDLServiceRecommendation recommandation = new WSDLServiceRecommendation();
					recommandation.serviceName = serviceName;					
					recommandation.similarity = similarity;
					
					recommendations.add(recommandation);
				}
			}

			// Order descending
			recommendations.sort(new Comparator<WSDLServiceRecommendation>() {
				@Override
				public int compare(WSDLServiceRecommendation o1, WSDLServiceRecommendation o2) {
					return  Double.compare(o2.similarity, o1.similarity);
				}
			});
			
			BPMNActivity activity = new BPMNActivity();
			activity.name = activityName;
			activity.recommendations = recommendations;

			activities.add(activity);
		}

		return activities.toArray(new BPMNActivity[activities.size()]);
	}

	/**
	 * Calculates the similarity (a number within 0 and 1) between two strings.
	 */
	private double similarity(String s1, String s2) {
		String longer = s1, shorter = s2;
		if (s1.length() < s2.length()) { // longer should always have greater
											// length
			longer = s2;
			shorter = s1;
		}
		int longerLength = longer.length();
		if (longerLength == 0) {
			return 1.0;
			/* both strings are zero length */ }
		/*
		 * // If you have StringUtils, you can use it to calculate the edit
		 * distance: return (longerLength -
		 * StringUtils.getLevenshteinDistance(longer, shorter)) / (double)
		 * longerLength;
		 */
		return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

	}

	/**
	 * Example implementation of the Levenshtein Edit Distance See
	 * http://rosettacode.org/wiki/Levenshtein_distance#Java
	 */
	private int editDistance(String s1, String s2) {
		s1 = s1.toLowerCase();
		s2 = s2.toLowerCase();

		int[] costs = new int[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			int lastValue = i;
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0)
					costs[j] = j;
				else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (s1.charAt(i - 1) != s2.charAt(j - 1))
							newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
						costs[j - 1] = lastValue;
						lastValue = newValue;
					}
				}
			}
			if (i > 0)
				costs[s2.length()] = lastValue;
		}
		return costs[s2.length()];
	}
}

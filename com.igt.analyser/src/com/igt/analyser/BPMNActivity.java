package com.igt.analyser;

import java.util.List;

public class BPMNActivity {
	public String name;
	public List<WSDLServiceRecommendation> recommendations;
	public int relevantServiceCount;
	public double precision, recall, fScore;
	
	@Override
	public String toString() {
		return name;
	}
}

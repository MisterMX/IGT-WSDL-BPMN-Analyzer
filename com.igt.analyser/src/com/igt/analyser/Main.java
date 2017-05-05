package com.igt.analyser;

import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.plugins.bmp.BMPImageWriteParam;

public class Main {

	public static void main(String[] args) {
		try {
			if (args.length < 3) {
				throw new IllegalArgumentException();
			}

			File bpmnFile = new File(args[1]);
			File wsdlFile = new File(args[2]);
			
			WSDLParser wsdlService = new WSDLParserImpl();
			BPMNParser bpmnService = new BPMNParserImpl();
			Analyser analyser = new AnalyserImpl();

			String[] serviceNames = wsdlService.getServiceNames(wsdlFile.getAbsolutePath());
			String[] activityNames = bpmnService.getActivities(bpmnFile.getAbsolutePath());
			
			BPMNActivity[] activities = analyser.getRecommendedServices(activityNames, serviceNames);
			
			for (BPMNActivity activity : activities) {
				System.out.println(activity.name);
				
				for (WSDLServiceRecommendation recommendation : activity.recommendations) {
					System.out.println("\t" + recommendation.serviceName);
					System.out.println("\t\tPrecision: " + recommendation.precision);
					System.out.println("\t\tRecall: " + recommendation.recall);
				}
			}
		} catch (IllegalArgumentException ex) {
			System.err.println("Usage: <bpmn-file> <wsdl-file>"); }
//		} catch (FileNotFoundException ex) {
//			System.err.println(ex.getMessage());
//		}
	}
}

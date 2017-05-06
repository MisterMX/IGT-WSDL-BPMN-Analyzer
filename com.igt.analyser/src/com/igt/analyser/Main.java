package com.igt.analyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
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
					System.out.println(String.format("\t%1$s (%2$.2f %%)", recommendation.serviceName, recommendation.similarity * 100));
				}
			}
		} catch (IllegalArgumentException ex) {
			System.err.println("Usage: <bpmn-file> <wsdl-file>");
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
	}
}

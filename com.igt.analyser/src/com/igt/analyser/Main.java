package com.igt.analyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.igt.analyser.gui.FileSelectorFrame;

public class Main {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//		try {
////			if (args.length < 3) {
////				throw new IllegalArgumentException();
////			}
//
//			File bpmnFile = new File("res/sig-test.bpmn"); // new File(args[0]);
//			File wsdlFile = new File("res/wheather.wsdl"); // new File(args[1]);
//			File testFile = new File("res/wheather-test.xml"); // new File(args[2]);
//			
//			WSDLParser wsdlService = new WSDLParserImpl();
//			BPMNParser bpmnService = new BPMNParserImpl();
//			Analyser analyser = new AnalyserImpl();
//			TestService testService = new TestServiceImpl();
//
//			String[] serviceNames = wsdlService.getServiceNames(wsdlFile.getAbsolutePath());
//			String[] activityNames = bpmnService.getActivities(bpmnFile.getAbsolutePath());
//			Map<String, Set<String>> activityServiceConnections = testService.getActivityServiceMapping(testFile.getAbsolutePath());
//			
//			BPMNActivity[] activities = analyser.getRecommendedServices(activityNames, serviceNames);
//			
//			for (BPMNActivity activity : activities) {
//				System.out.println(activity.name);
//				
//				Set<String> relevantServices = activityServiceConnections.get(activity.name);
//				
//				int relevantServiceCount = 0;
//				
//				System.out.println(String.format(" %d service(s) found.", activity.recommendations.size()));
//				for (WSDLServiceRecommendation recommendation : activity.recommendations) {
//					String serviceText = String.format("%1$s (%2$.2f %%)", recommendation.serviceName, recommendation.similarity * 100);
//
//					// Is relevant?
//					if (relevantServices != null && relevantServices.contains(recommendation.serviceName)) {
//						relevantServiceCount++;
//						System.out.println("\t+ " + serviceText);
//					} else {
//						System.out.println("\t- " + serviceText);
//					}
//				}
//				
//				double precision = activity.recommendations.size() > 0
//						? (double)relevantServiceCount / activity.recommendations.size() * 100
//						: 0;
//				double recall = relevantServices != null
//						? (double)relevantServiceCount / relevantServices.size() * 100
//						: 0;
//				
//				double fScore = precision + recall != 0f
//						? 2 * precision * recall / (precision + recall)
//						: 0;
//				
//				System.out.println(String.format(" - Recall: %1$.2f %%", recall));
//				System.out.println(String.format(" - Precision: %1$.2f %%", precision));
//				System.out.println(String.format(" - F-Measure: %1$.2f %%", fScore));
//				System.out.println();
//			}
//		} catch (IllegalArgumentException ex) {
//			System.err.println("Usage: <bpmn-file> <wsdl-file> <test-file>");
//		} catch (FileNotFoundException ex) {
//			System.err.println(ex.getMessage());
//		}
		
		FileSelectorFrame frame = new FileSelectorFrame();
		frame.setVisible(true);
	}
}

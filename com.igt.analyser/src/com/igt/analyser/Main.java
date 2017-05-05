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

			String[] serviceNames = wsdlService.getServiceNames(wsdlFile.getAbsolutePath());
			String[] activityNames = bpmnService.getActivities(bpmnFile.getAbsolutePath());
			
			

		} catch (IllegalArgumentException ex) {
			System.err.println("Usage: <bpmn-file> <wsdl-file>");
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
		}
	}
}

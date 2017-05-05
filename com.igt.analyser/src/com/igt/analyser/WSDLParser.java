package com.igt.analyser;

public interface WSDLParser {
	/**
	 * Gets the names of the services defined in the following WSDL file.
	 * @param filepath
	 * @return
	 */
	String[] getServiceNames(String filepath);
}

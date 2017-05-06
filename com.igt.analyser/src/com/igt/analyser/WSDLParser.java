package com.igt.analyser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface WSDLParser {
	/**
	 * Gets the names of the services defined in the following WSDL file.
	 * @param filepath
	 * @return
	 */
	String[] getServiceNames(String filepath) throws ParserConfigurationException, SAXException, IOException;
}

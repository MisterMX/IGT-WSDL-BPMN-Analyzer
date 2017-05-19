package com.igt.analyser;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface TestService {
	/**
	 * Gets the predefined activity service mapping.
	 * @param filepath
	 * @return
	 */
	Map<String, Set<String>> getActivityServiceMapping(String filepath) throws ParserConfigurationException, SAXException, IOException;
}

package com.igt.analyser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface BPMNParser {
	/**
	 * Gets activities defined in the following BPMN XML file.
	 * @param filepath
	 * @return
	 */
	String[] getActivities(String filepath) throws ParserConfigurationException, SAXException, IOException;
}

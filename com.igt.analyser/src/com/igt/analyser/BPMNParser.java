package com.igt.analyser;

public interface BPMNParser {
	/**
	 * Gets activities defined in the following BPMN XML file.
	 * @param filepath
	 * @return
	 */
	String[] getActivities(String filepath);
}

package com.igt.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestServiceImpl implements TestService {

	@Override
	public Map<String, Set<String>> getActivityServiceMapping(String filepath) throws ParserConfigurationException, SAXException, IOException {
		Document doc = XMLUtils.getXmlDocument(filepath);

		// optional, but recommended
		// read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		
		NodeList activityList = doc.getDocumentElement().getChildNodes();
		
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		
		for (int temp = 0; temp < activityList.getLength(); temp++) {
			Node activityNode = activityList.item(temp);

			if (activityNode.getNodeType() == Node.ELEMENT_NODE && activityNode.getNodeName().contains("activity")) {
				String activityName = activityNode.getAttributes().getNamedItem("name").getTextContent();

				NodeList serviceList = activityNode.getChildNodes();
				
				Set<String> serviceNames = new HashSet<String>();
				
				for (int i = 0; i < serviceList.getLength(); i++) {
					Node serviceNode = serviceList.item(i);
					
					if (serviceNode.getNodeType() == Node.ELEMENT_NODE && serviceNode.getNodeName().contains("service")) {
						String serviceName = serviceNode.getAttributes().getNamedItem("name").getTextContent();
						
						serviceNames.add(serviceName);
					}
				}
				
				map.put(activityName, serviceNames);
			}
		}

		return map;
	}
}

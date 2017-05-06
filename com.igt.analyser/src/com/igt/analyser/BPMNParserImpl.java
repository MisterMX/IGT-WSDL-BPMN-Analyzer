package com.igt.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BPMNParserImpl implements BPMNParser {

	@Override
	public String[] getActivities(String filepath) throws ParserConfigurationException, SAXException, IOException {
//		return new String[] { "Get all users", "Get the current wheather data", "Create an admin user"}; 
		
		Document doc = XMLUtils.getXmlDocument(filepath);

		// optional, but recommended
		// read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("task");

		List<String> activityNames = new ArrayList<String>();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				String name = nNode.getAttributes().getNamedItem("name").getTextContent();

				activityNames.add(name);
			}
		}

		return activityNames.toArray(new String[activityNames.size()]);
	}

}

package com.igt.analyser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WSDLParserImpl implements WSDLParser {

	@Override
	public String[] getServiceNames(String filepath) throws ParserConfigurationException, SAXException, IOException {
		// return new String[] {"GetWheather", "GetCustomers",
		// "GetDistinctUserNames", "CreateAdminUser"};

		Document doc = XMLUtils.getXmlDocument(filepath);

		// optional, but recommended
		// read this -
		// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getDocumentElement().getChildNodes();

		List<String> serviceNames = new ArrayList<String>();

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE && nNode.getNodeName().contains("port")) {
				String name = nNode.getAttributes().getNamedItem("name").getTextContent();

				serviceNames.add(name);
			}
		}

		return serviceNames.toArray(new String[serviceNames.size()]);
	}
}

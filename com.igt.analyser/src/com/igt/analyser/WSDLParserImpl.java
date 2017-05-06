package com.igt.analyser;

public class WSDLParserImpl implements WSDLParser {

	@Override
	public String[] getServiceNames(String filepath) {
		return new String[] {"GetWheather", "GetCustomers", "GetDistinctUserNames", "CreateAdminUser"};
	}

}

package com.xyz.qname;

import javax.xml.ws.Endpoint;

public class PublishMyService {

	public static void main(String[] args){
		String address="http://localhost:8088/ns";

		Endpoint.publish(address, new MyServiceImpl());
	}

}

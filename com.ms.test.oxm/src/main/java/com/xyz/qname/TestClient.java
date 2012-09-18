package com.xyz.qname;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TestClient {

	public static void main(String [] args){

		URL url;
		try {
			url = new URL("http://localhost:8088/ns?wsdl");
			QName sName = new QName("http://service.decarl.org/", "MyServiceImplService");
			Service service = Service.create(url, sName);
			IMyService ms = service.getPort(IMyService.class);
			System.out.println(ms.add(22, 44));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}

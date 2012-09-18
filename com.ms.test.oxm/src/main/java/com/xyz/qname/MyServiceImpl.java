package com.xyz.qname;
import static java.lang.System.out;

import javax.jws.WebService;

@WebService(endpointInterface = "com.xyz.qname.IMyService")
public class MyServiceImpl implements IMyService {

	public int add(int a, int b) {
		out.println(a + "+" + b + "=" + (a + b) );
        return a + b;
	}

	public int minus(int a, int b) {
		out.println(a + "-" + b + "=" + (a - b) );
        return a - b;
	}

}

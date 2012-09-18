package com.xyz.qname;

import javax.jws.WebService;

@WebService
public interface IMyService {

	public int add(int a, int b);
	public int minus(int a, int b);
}

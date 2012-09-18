package moringstarSoft.jmxDemo;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent {
	private MBeanServer mbs = null;

	public HelloAgent() {
		//create a MBeanServer
		mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
		//create an adapter
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		//create a MBean
		HelloWorld hw = new HelloWorld("hello boys!");
		HelloWorld hw1 = new HelloWorld("hello world!");
		
		ObjectName adapterName = null;
		ObjectName helloWorldName = null;
		ObjectName helloWorldName1 = null;

		try {
			adapterName = new ObjectName(
					"HelloAgent:name=htmladapter,port=9092");
			//regisetr the adapter to the MBeanServer
			mbs.registerMBean(adapter, adapterName);
			//declare the port which the adapter user
			adapter.setPort(9092);
			//start the adapter
			adapter.start();

			helloWorldName = new ObjectName("HelloAgent:name=helloWorld1");
			helloWorldName1 = new ObjectName("HelloAgent:name=helloWorld2");
			mbs.registerMBean(hw, helloWorldName);
//			mbs.registerMBean(hw, helloWorldName1);
			mbs.registerMBean(hw1, helloWorldName1);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		//declare the agent and start the adapter
		HelloAgent agent = new HelloAgent();

	}

}

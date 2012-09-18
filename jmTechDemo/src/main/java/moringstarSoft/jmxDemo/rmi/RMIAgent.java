package moringstarSoft.jmxDemo.rmi;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import moringstarSoft.jmxDemo.HelloWorld;

import com.sun.jdmk.comm.RmiConnectorServer;

public class RMIAgent {

	public static void main(String[] args) {
		MBeanServer mbs = MBeanServerFactory.createMBeanServer("HelloAgent");
		RmiConnectorServer connector = new RmiConnectorServer();
		ObjectName connectorName = null;
		try {
			connectorName = new ObjectName("HelloAgent:name=RMIConnector");
			mbs.registerMBean(connector, connectorName);
			HelloWorld hw = new HelloWorld("hello boys!");
			ObjectName helloWorldName = new ObjectName(
					"HelloAgent:name=helloWorld1");

			mbs.registerMBean(hw, helloWorldName);
			connector.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

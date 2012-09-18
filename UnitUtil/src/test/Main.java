package test;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Child1 c1 = new Child1();
		Child2 c2 = new Child2();
		c1.add();
		c1.say();
		c1.process();
		c2.say();
		c2.process();

	}

}

package test;

public abstract class Parent {
	protected int id = 1;
	
	public void process(){
		System.out.println("parent: "+this.id);
	}
}

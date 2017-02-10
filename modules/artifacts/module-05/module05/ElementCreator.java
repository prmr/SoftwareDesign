package module05;

/**
 * A mock-up that demonstrates the use of the Prototype Design Pattern. 
 */
public class ElementCreator
{	
	private Element aPrototype;
	
	public ElementCreator(Element pPrototype)
	{
		aPrototype = pPrototype;
	}
	
	public Element createElement()
	{
		return aPrototype.clone();
	}
}

interface Element extends Cloneable
{
	void doSomething();
	Element clone();
}
class Element1 implements Element
{
	public void doSomething() {}
	public Element1 clone() { return null; }

}

class Element2 implements Element
{
	public void doSomething() {}
	public Element2 clone() { return null; }

}

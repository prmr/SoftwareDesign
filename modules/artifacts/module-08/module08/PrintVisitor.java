package module08;

import java.util.Iterator;

public class PrintVisitor implements Visitor
{
	
	private final StringBuilder aPrefix = new StringBuilder();
	private static final String TAB = "   ";
	
	private void increaseTab()
	{
		aPrefix.append(TAB);
	}
	
	private void decreaseTab()
	{
		aPrefix.delete(aPrefix.length()-TAB.length(), aPrefix.length());
	}
	
	private String tab()
	{
		return aPrefix.toString();
	}

	@Override
	public void visitUniversity(University pUniversity)
	{
		System.out.println(pUniversity.getName());
		increaseTab();
		for( Iterator<Faculty> i = pUniversity.getFaculties(); i.hasNext(); )
		{
			i.next().accept(this);
		}
		decreaseTab();
		
	}

	@Override
	public void visitFaculty(Faculty pFaculty)
	{
		System.out.println(tab() + pFaculty.getName());
		increaseTab();
		for( Iterator<Department> i = pFaculty.getDepartments(); i.hasNext(); )
		{
			i.next().accept(this);
		}
		
		for( Iterator<Committee> i = pFaculty.getCommittees(); i.hasNext(); )
		{
			i.next().accept(this);
		}
		decreaseTab();
		
		
	}

	@Override
	public void visitDepartment(Department pDepartment)
	{
		System.out.println(tab() + pDepartment.getName());
		increaseTab();
		for( Iterator<Committee> i = pDepartment.getCommittees(); i.hasNext(); )
		{
			i.next().accept(this);
		}
		decreaseTab();
	}

	@Override
	public void visitCommittee(Committee pCommittee)
	{
		System.out.println(tab() + "C: " + pCommittee.getName());
		increaseTab();
		for( Iterator<Committee> i = pCommittee.getCommittees(); i.hasNext(); )
		{
			i.next().accept(this);
		}
		decreaseTab();
	}	
}

package module08;

import java.util.Iterator;

/**
 * Default implementation of the structure traversal in the Visitor.
 * Here we can get rid of double dispatch and call visit directly from
 * within visit, but only because of special circumstances: we know the 
 * specific concrete types of elements we are visiting. This is not always 
 * the case, especially in object graphs generated from recursive structures.
 */
public class DefaultVisitor implements Visitor
{
	@Override
	public void visitUniversity(University pUniversity)
	{
		for( Iterator<Faculty> i = pUniversity.getFaculties(); i.hasNext(); )
		{
			i.next().accept(this);
		}	
	}

	@Override
	public void visitFaculty(Faculty pFaculty)
	{
		for( Iterator<Department> i = pFaculty.getDepartments(); i.hasNext(); )
		{
			i.next().accept(this);
		}
		
		for( Iterator<Committee> i = pFaculty.getCommittees(); i.hasNext(); )
		{
			i.next().accept(this);
		}
	}

	@Override
	public void visitDepartment(Department pDepartment)
	{
		for( Iterator<Committee> i = pDepartment.getCommittees(); i.hasNext(); )
		{
			i.next().accept(this);
		}
	}

	@Override
	public void visitCommittee(Committee pCommittee)
	{
		
		for( Iterator<Committee> i = pCommittee.getCommittees(); i.hasNext(); )
		{
			i.next().accept(this);
		}
	}	
}

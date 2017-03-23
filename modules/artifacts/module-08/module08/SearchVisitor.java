package module08;

public class SearchVisitor extends DefaultVisitor
{
	private final String aQuery;
	private OrgNode aResult = new NullOrgNode();
	
	public SearchVisitor(String pQuery)
	{
		aQuery = pQuery;	
	}
	
	OrgNode getResult() {return aResult; }
	
	
	@Override
	public void visitCommittee(Committee pCommittee)
	{
		if( pCommittee.getName().equals(aQuery))
		{
			aResult = pCommittee;
		}
		super.visitCommittee(pCommittee);
	}		

}



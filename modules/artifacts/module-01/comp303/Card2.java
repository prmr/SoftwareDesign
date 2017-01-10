package comp303;

/**
 * Improves (?) on Card1 by trying to hide our decision
 * concerning the internal representation of a card.
 * A small example of information hiding.
 */
public class Card2
{
	private int aId = 0; 
	
	/**
	 * @param pCardId [0,52] inclusive
	 */
	public Card2(int pCardId)
	{
		aId = pCardId;
	}
	
	/**
	 * @return [0,52] the id of the card
	 */
	public int getId()
	{
		return aId;
	}
	
	/**
	 * @param pId [0,52] the id of the card
	 */
	public void setId(int pId)
	{
		aId = pId;
	}
}

public abstract class Jewel //Abstract because we don't want objects of this. It is for Polymorphism.
{
	private String icon; //Icon representative of jewel
	private String[] matchType; //Holds the types that a jewel matches with
	private int[] matchDirection; //Holds the directions that a jewel matches with
	private int value; //Point value of jewel
	
	public Jewel(String icon)
	{
		this.icon = icon;
	}

	//GETTER-SETTERS
	public String getIcon() 
	{
		return icon;
	}

	public void setIcon(String icon) 
	{
		this.icon = icon;
	}

	public String[] getMatchType() 
	{
		return matchType;
	}

	public void setMatchType(String[] matchType) 
	{
		this.matchType = matchType;
	}

	public int[] getMatchDirection() 
	{
		return matchDirection;
	}

	public void setMatchDirection(int[] matchDirection) 
	{
		this.matchDirection = matchDirection;
	}

	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		this.value = value;
	}
	//
	
}
public class LetterJewel extends Jewel
{
	private String[] matchType;
	private int[] matchDirection;
	private int value;
	
	public LetterJewel(String icon)
	{
		super(icon);
		//Num-Pad directions for matches
		switch(icon) 
		{
			case "D":
				this.value = 30;
				this.matchType = new String[] {"D"};
				this.matchDirection = new int[] {7,3,9,1};
				break;
			case "S":
				this.value = 15;
				this.matchType = new String[] {"S"};
				this.matchDirection = new int[] {4,6};
				break;
			case "T":
				this.value = 15;
				this.matchType = new String[] {"T"};
				this.matchDirection = new int[] {8,2};
				break;
			case "W":
				this.value = 10;
				this.matchType = new String[] {"D","S","T","W"};
				this.matchDirection = new int[] {8,2,4,6,7,3,9,1};
				break;
		}
		//
	}
	
	@Override
	public String toString()
	{
		return this.getIcon();
	}
	
	//GETTER-SETTERS
	public String[] getMatchType()
	{
		return this.matchType;
	}
	public int[] getMatchDirection()
	{
		return this.matchDirection;
	}
	public int getValue()
	{
		return this.value;
	}
	//
}
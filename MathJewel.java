public class MathJewel extends Jewel
{
	private String[] matchType = {"/", "-", "+", "\\", "|"};
	private int[] matchDirection;
	private int value = 20;
	
	public MathJewel(String icon)
	{
		super(icon);
		//Num-Pad directions for matches
		switch(icon)
		{
			case "/":
				this.matchDirection = new int[] {9,1};
				break;
			case "-":
				this.matchDirection = new int[] {4,6};
				break;
			case "+":
				this.matchDirection = new int[] {4,6,8,2};
				break;
			case "\\":
				this.matchDirection = new int[] {7,3};
				break;
			case "|":
				this.matchDirection = new int[] {8,2};
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

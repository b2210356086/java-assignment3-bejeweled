public class EmptyJewel extends Jewel
{
	public EmptyJewel()
	{
		super(" "); //Icon is space bar
	}
	
	@Override
	public String toString()
	{
		return this.getIcon();
	}
}
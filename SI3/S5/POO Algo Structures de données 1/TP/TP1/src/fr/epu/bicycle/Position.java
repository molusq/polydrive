package fr.epu.bicycle;

/**
 * Position of electric bicycle
 * @author Marc Pinet
 */
public class Position
{
	private int x;
	private int y;
	private static final double EPSILON = 0.001;
	
	public Position()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean isEquivalent(Position p)
	{
		return Math.abs(x - p.x) < EPSILON && Math.abs(y - p.y) < EPSILON;
	}
	
	public int[] getPosition()
	{
		return new int[]{x, y};
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}

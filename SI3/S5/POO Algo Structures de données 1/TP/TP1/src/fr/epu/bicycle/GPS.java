package fr.epu.bicycle;

/**
 * GPS of electric bicycle
 * @author Marc Pinet
 */
public class GPS
{
	private Position position;
	
	public GPS()
	{
		this.position = new Position();
	}
	
	public void move(int x, int y)
	{
		this.position = new Position(x, y);
	}
	
	public Position getPosition()
	{
		return position;
	}
}

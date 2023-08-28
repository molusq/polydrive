package fr.epu.bicycle;

/**
 * Battery of electric bicycle
 * @author Marc Pinet
 */
public class Battery
{
	private int capacite;
	private int niveau;
	
	public Battery(int capacite)
	{
		this.capacite = capacite;
		this.niveau = capacite;
	}
	
	public int getCapacite()
	{
		return capacite;
	}
	
	public int getNiveau()
	{
		return niveau;
	}
}

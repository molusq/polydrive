package fr.epu.bicycle;

/**
 * EBike implements the computer part of an EBike that would have a battery and a location.
 * @author Marc Pinet
 */
public class EBike
{
	private int km;
	private GPS gps;
	private Battery battery;
	protected static final int INITIAL_DISTANCE = 1;
	protected static final int INITIAL_CHARGE = 100;
	
	public EBike() {
		km = INITIAL_DISTANCE;
		gps = new GPS();
		battery = new Battery(INITIAL_CHARGE);
	}
	
	/**
	 * Modifies the number of km traveled by adding <code>nbKmToAdd</code> km.
	 * @param nbKmToAdd
	 */
	public void addKm(int nbKmToAdd){
		if(nbKmToAdd > 0) {
			km += nbKmToAdd;
		}
	}
	
	public int getKm()
	{
		return km;
	}
	
	private void setKm(int km)
	{
		this.km = km;
	}
	
	
	public GPS getGps()
	{
		return gps;
	}
	
	public void setGps(GPS gps)
	{
		this.gps = gps;
	}
	
	public Battery getBattery()
	{
		return battery;
	}
	
	public void setBattery(Battery battery)
	{
		this.battery = battery;
	}
}
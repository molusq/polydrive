package fr.epu.bicycle;

import java.util.Objects;

/**
 * Battery of an electric bicycle
 *
 * @author Marc Pinet
 */
public class Battery {
	private final int capacite;
	private int niveau;
	
	public Battery(int capacite) {
		this.capacite = capacite;
		this.niveau = capacite;
	}
	
	public void ajouterCharge(int charge) {
		niveau += charge;
		if(niveau > capacite)
			niveau = capacite;
	}
	
	public int getCapacite() {
		return capacite;
	}
	
	public int getNiveau() {
		return niveau;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(!(o instanceof Battery battery))
			return false;
		return getCapacite() == battery.getCapacite() && getNiveau() == battery.getNiveau();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getCapacite(), getNiveau());
	}
}

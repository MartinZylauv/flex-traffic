package domain;

public interface Bil {
	
	/**
	 * @author Steffen
	 * Domainclass for the car object used for assigning cars to ordered trips.
	 * 
	 */

	public int getID();
	
	public void setID(int ID);
	
	public int getKapacitet();
	
	public void setKapacitet(int kapacitet);
	
	public String getNummerplade();
	
	public void setNummerplade(String nummerplade);
}

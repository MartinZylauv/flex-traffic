package domain;

public class BilImpl implements Bil {

	int ID;
	int kapacitet;
	String nummerplade;
	
	@Override
	public int getID() {

		return ID;
	}

	@Override
	public void setID(int ID) {
	this.ID=ID;

	}

	@Override
	public int getKapacitet() {
		
		return kapacitet;
	}

	@Override
	public void setKapacitet(int kapacitet) {
		this.kapacitet = kapacitet;

	}

	@Override
	public String getNummerplade() {
	
		return nummerplade;
	}

	@Override
	public void setNummerplade(String nummerplade) {
	this.nummerplade =nummerplade;

	}

}

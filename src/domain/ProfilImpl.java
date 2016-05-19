package domain;

public class ProfilImpl implements Profil {

	long kundeNummer;
	String fuldtNavn;
	String email;
	long tlfNummer;
	String cpr;

	@Override
	public long getKundeNummer() {
		return kundeNummer;
	}

	@Override
	public void setKundeNummer(long kundeNummer) {
		this.kundeNummer = kundeNummer;
	}

	@Override
	public String getFuldtNavn() {
		return fuldtNavn;
	}

	@Override
	public void setFuldtNavn(String fuldtNavn) {
		this.fuldtNavn = fuldtNavn;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;

	}

	@Override
	public long getTlfNummer() {

		return tlfNummer;
	}

	@Override
	public void setTlfNummer(long tlfNummer) {
		this.tlfNummer = tlfNummer;

	}

	@Override
	public void setCPR(String cpr) {
		this.cpr=cpr;
		
	}

	@Override
	public String getCPR() {
	
		return cpr;
	}

}

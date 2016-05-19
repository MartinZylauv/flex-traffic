package presentation;

public class LoggedIn {

	private int kundenummer;
	private boolean erAdmin;

	public void setkundenummer(int kundenummer) {
		this.kundenummer = kundenummer;
	}

	public int getKundenummer() {
		return kundenummer;
	}
	
	public void setAdmin(boolean erAdmin){
		this.erAdmin = erAdmin;
	}
	
	public boolean getAdmin(){
		return erAdmin;
	}

}

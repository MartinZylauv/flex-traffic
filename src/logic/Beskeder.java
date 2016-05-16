package logic;

public enum Beskeder {
	MANGLER(0, "Indtast venligst følgende felter før du kan bestille din kørsel: "), BESTILSUCCESS(1,
			"Din bestilling blev succesfuldt udført!"), VALIDERINGSFEJL(2,
					"Der var en ukendt fejl med dine informationer. Prøv vejligst igen");

	private final int beskedskode;
	private final String description;

	private Beskeder(int code, String description) {
		this.beskedskode = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return beskedskode;
	}

	@Override
	public String toString() {
		return beskedskode + ": " + description;
	}

}

// TODO : inspiration fra
// http://stackoverflow.com/questions/446663/best-way-to-define-error-codes-strings-in-java
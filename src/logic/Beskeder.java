package logic;

public enum Beskeder {
	MANGLER("Indtast venligst følgende felter før du kan bestille din kørsel: "), BESTILSUCCESS(
			"Din bestilling blev succesfuldt udført!"), 
	VALIDERINGSFEJL("Der var en ukendt fejl med dine informationer. Prøv vejligst igen"),
	NAVN_IKKE_INDTASTET("Indtast venligst dit navn. Dette felt kan ikke være tomt."),
	EMAIL_IKKE_INDTASTET("Indtast venligst din email. Dette felt kan ikke være tomt."),
	TLF_IKKE_INDTASTET("Indtast venligst dit telefonnummer. Dette felt kan ikke være tomt."),
	UKENDT_SQL("Der skete en uventet fejl. Prøv venligst at genstarte programmet, eller at kontakte kundeservice."),
	UKENDT_KUNDENUMMER("Der findes ingen kunde med kundenummret du angav. Prøv venligst igen."),
	UKENDT_FEJL("Der skete en ukendt fejl. Prøv venligst at genstarte programmet, eller at kontakte kundeservice."),
	
	START_ADR_MANGLER(""),
	START_POSTNR_MANGLER(""),
	START_BY_MANGLER(""),
	SLUT_ADR_MANGLER(""),
	SLUT_POSTNR_MANGLER(""),
	SLUT_BY_MANGLER(""),
	
	KM_MANGLER(""),
	
	DATO_MANGLER("");		//TODO skriv beskeder der mangler.


	private final String description;

	private Beskeder(String description) {
		
		this.description = description;
	}

	public String getDescription() {
		return description;
	}



	@Override
	public String toString() {
		return  description;
	}

}

// TODO : inspiration fra
// http://stackoverflow.com/questions/446663/best-way-to-define-error-codes-strings-in-java
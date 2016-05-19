package logic;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.KoerselHistorikImpl;
import domain.KoerselImpl;
import domain.Profil;
import domain.ProfilImpl;
import domain.SlutDestination;
import domain.SlutDestinationImpl;
import domain.StartDestination;
import domain.StartDestinationImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import persistence.KoerselsKartotek;
import persistence.KoerselsKartotekImpl;
import persistence.ProfilKartotekImpl;
import sats.UnknownKommuneException;

public class FTPControllerImpl extends Observable implements FTPController, Observer {

	
	long kundenummer;
	PrisBeregnerImpl pb;
	double pris = 0;
	Tilstande tilstand;
	KoerselImpl koersel = new KoerselImpl();
	StartDestination startdestination = new StartDestinationImpl();
	SlutDestination slutdestination = new SlutDestinationImpl();
	Time tid;
	double antalKm;

	@Override
	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, Date dato,
			int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer, int brugerNummer, Time tid,
			double antalKm) throws InvalidInformationException {

		Validator validator = new ValidatorImpl();
		koersel.setAntalBagage(antalBagage);
		koersel.setAntalPersoner(antalPersoner);
		koersel.setBrugerNummer(brugerNummer);
		koersel.setDato(dato);
		koersel.setTime(tid);
		koersel.setAntalKm(antalKm);
		koersel.setHjaelplemidler(antalHjaelpemidler);
		koersel.setKommentar(kommentarer);
		koersel.setPris(pris);
		koersel.setAntalKm(antalKm);
		this.startdestination = startDestination;
		this.slutdestination = slutDestination;
		this.tid = tid;
		this.antalKm = antalKm;

		validator.angivInformationer(startDestination, slutDestination, koersel);

		if (validator.validerInformationer() == false) {
			throw new InvalidInformationException(Beskeder.VALIDERINGSFEJL.getDescription());
		} 
	}

	@Override
	public Beskeder accepterPris() throws SQLException {
		Beskeder besked = null;
		KoerselsKartotek koerselskartotek = new KoerselsKartotekImpl();
		koerselskartotek.gemKoersel(startdestination, slutdestination, koersel, tid);
		besked = Beskeder.BESTILSUCCESS;
		return besked;
	}

	@Override
	public Profil anmodOmProfil(long kundeNummer) throws SQLException {
		ProfilKartotekImpl profilkartotek = new ProfilKartotekImpl();
		Profil profil = new ProfilImpl();
		profil = profilkartotek.anmodOmProfil(kundeNummer);
		return profil;
	}

	@Override
	public void update(Observable o, Object arg) {

		this.pris = pb.getPris();

		tilstand = Tilstande.BEREGNET;

	}

	@Override
	public void getPrisTilbud(StartDestination startdestination, SlutDestination slutdestination, double km, Date dato)
			throws SQLException, UnknownKommuneException, InvalidInformationException {
		Validator validator =  new ValidatorImpl();
		validator.validerTilbud(startdestination, slutdestination, km, dato);
		pb = new PrisBeregnerImpl(startdestination, slutdestination, km,dato);
		pb.addObserver(this);
		(new Thread(pb)).start(); // TODO REFACTOR
		
	}

	@Override
	public double getPris() {
		return pris;

	}

	@Override
	public void setPris(double pris) {
		this.pris = pris;

	}

	@Override
	public long getKundenummer() {
		return kundenummer;

	}

	@Override
	public void setKundenummer(long kundenummer) {
		this.kundenummer = kundenummer;
	}

	@Override
	public void indtastNyeInformationer(String fuldtNavn, String email, long tlfNummer) throws SQLException, InvalidInformationException {
		Validator validator = new ValidatorImpl();
		
			 if(validator.validerProfilRediger(fuldtNavn, email, tlfNummer)){
				 ProfilKartotekImpl profilkartotek = new ProfilKartotekImpl();
				profilkartotek.redigerProfil(fuldtNavn, email, tlfNummer, kundenummer);
		}
		

	}

	@Override
	public ArrayList<KoerselHistorikImpl> anmodOmBrugeresKørselHistorik(int kundenummer, Date dato1, Date dato2) throws SQLException {
		KoerselsKartotekImpl koerselkartotek = new KoerselsKartotekImpl();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList<>();
		if(kundenummer == 0){
			if(dato1 == null || dato2 == null){
				liste = koerselkartotek.visFlereBrugerKørsler();
			}else{
			liste = koerselkartotek.visFlereBrugerKørslerTidsinterval(dato1, dato2);
			}
		}else{
			if(dato1 == null || dato2 == null){
			liste = koerselkartotek.visEnkeltBrugerKørsler(kundenummer);
			} else{
				liste = koerselkartotek.visEnkeltBrugerKørslerTidsinterval(kundenummer, dato1, dato2);
			}
		}
		
		
		return liste;
	}

	@Override
	public boolean checkProfil(long kundenummer) throws SQLException {
		ProfilKartotekImpl profilkartotek = new ProfilKartotekImpl();
		if(profilkartotek.checkProfil(kundenummer)){
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAdmin(long kundenummer) throws SQLException {
		ProfilKartotekImpl profilkartotek = new ProfilKartotekImpl();
		
		return profilkartotek.checkAdmin(kundenummer);
	}

};
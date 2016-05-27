package logic;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import domain.Bil;
import domain.Koersel;
import domain.KoerselHistorik;
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
import persistence.BilKartotekImpl;
import persistence.DBCreator;
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
	int bil;

	@Override
	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, Date dato,
			int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer, int brugerNummer, Time tid,
			double antalKm, boolean erAdmin) throws InvalidInformationException {

		Validator validator = new ValidatorImpl();
		koersel.setAntalBagage(antalBagage);
		koersel.setAntalPersoner(antalPersoner);
		koersel.setBrugerNummer(brugerNummer);
		koersel.setDato(dato);
		koersel.setTime(tid);
		koersel.setAntalKm(antalKm);
		koersel.setHjaelplemidler(antalHjaelpemidler);
		if(erAdmin == false){
			koersel.setKommentar(kommentarer);
		} else{
			koersel.setAdminKommentar(kommentarer);
		}
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

		if(pb.getTilstand() == Tilstande.BEREGNINGSFEJL){
			this.pris = -1.0;
		} else {
		
		this.pris = pb.getPris();
		tilstand = Tilstande.BEREGNET;
		}
	}

	@Override
	public void getPrisTilbud(StartDestination startdestination, SlutDestination slutdestination, double km, Date dato)
			throws SQLException, UnknownKommuneException, InvalidInformationException {
		Validator validator =  new ValidatorImpl();
		validator.validerTilbud(startdestination, slutdestination, km, dato);
		pb = new PrisBeregnerImpl(startdestination, slutdestination, km,dato);
		pb.addObserver(this);
		try{
		(new Thread(pb)).start(); 
		} catch(Exception e){

		}
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

	@Override
	public int getBil() {
		
		return bil;
	}

	@Override
	public void tildelBil(int bil) {
		this.bil = bil;
		
	}

	

	@Override
	public void angivKoerselTilVedligeholdelse(KoerselHistorik koerselhistorik) throws SQLException, InvalidInformationException {
		KoerselsKartotekImpl koersel = new KoerselsKartotekImpl();
		if(!koerselhistorik.getErGodkendt()){
		koersel.godkendKoersel(bil, koerselhistorik);
		koersel.setGodkendtKoersel(bil, koerselhistorik);
		}else{
			throw new InvalidInformationException(Beskeder.BIL_ALLEREDE_TILDELT.getDescription());
		}
	}

	@Override
	public List<Integer> getBiler() throws SQLException {
		ArrayList<Integer> biler = new ArrayList<Integer>();
		BilKartotekImpl bilKartotek = new BilKartotekImpl();
		for(Bil bil : bilKartotek.getBiler()){
			biler.add(bil.getID());
		}
		return biler;
		
	}

	@Override
	public Integer getBilFraID(int ID) throws SQLException {
		Integer bil_id = 0;
		BilKartotekImpl bilKartotek = new BilKartotekImpl();
		bil_id = bilKartotek.getBilFromKoersel(ID);
		return bil_id;
	}

	@Override
	public void createDB() throws SQLException {
		DBCreator dbcreate = new DBCreator();
		dbcreate.createBilerDB();
		dbcreate.createProfilerDB();
		dbcreate.createKoerslerDB();
		dbcreate.createKoerselstildelingDB();
		dbcreate.insertProfil();
		dbcreate.insertBil();
		
	}

	@Override
	public void indtastKommentar(String kommentar, int ID) throws SQLException {
		KoerselsKartotekImpl koersel = new KoerselsKartotekImpl();
		koersel.gemKommentar(kommentar, ID);
		
	}

	@Override
	public String getKommentar(int ID) throws SQLException {
		KoerselsKartotekImpl koersel = new KoerselsKartotekImpl();
		String kommentar = koersel.getKommentar(ID);
		return kommentar;
	}
	
	@Override
	public ArrayList<KoerselHistorikImpl> anmodOmBrugeresKørselHistorikAfholdt(int kundenummer, Date dato1, Date dato2) throws SQLException {
		KoerselsKartotekImpl koerselkartotek = new KoerselsKartotekImpl();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList<>();
		if(kundenummer == 0){
			if(dato1 == null || dato2 == null){
				liste = koerselkartotek.visFlereBrugerKørslerAfholdt();
			}else{
			liste = koerselkartotek.visFlereBrugerKørslerTidsintervalAfholdt(dato1, dato2);
			}
		}else{
			if(dato1 == null || dato2 == null){
			liste = koerselkartotek.visEnkeltBrugerKørslerAfholdt(kundenummer);
			} else{
				liste = koerselkartotek.visEnkeltBrugerKørslerTidsintervalAfholdt(kundenummer, dato1, dato2);
			}
		}
		
		
		return liste;
	}

	@Override
	public void writeToCsv(ArrayList<KoerselHistorikImpl> koerselhistorik, File file, boolean erAdmin) throws IOException {
		CSVWriter csv = new CSVWriter();
		csv.writeToCSV(koerselhistorik, file, erAdmin);
	}

};
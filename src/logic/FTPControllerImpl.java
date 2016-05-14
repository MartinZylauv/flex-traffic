package logic;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import domain.Koersel;
import domain.KoerselImpl;
import domain.Profil;
import domain.ProfilImpl;
import domain.SlutDestination;
import domain.SlutDestinationImpl;
import domain.StartDestination;
import domain.StartDestinationImpl;
import persistence.KoerselsKartotek;
import persistence.KoerselsKartotekImpl;
import persistence.KommuneKartotekImpl;
import persistence.ProfilKartotekImpl;
import sats.UnknownKommuneException;

public class FTPControllerImpl extends Observable implements FTPController, Observer {

	PrisBeregnerImpl pb;
	double pris = 0;
	Tilstande tilstand;
	KoerselImpl koersel = new KoerselImpl();
	StartDestination startdestination = new StartDestinationImpl();
	SlutDestination slutdestination = new SlutDestinationImpl();
	Time tid;
	double antalKm;

	@Override
	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, LocalDate dato,
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
		this.startdestination = startDestination;
		this.slutdestination = slutDestination;
		this.tid= tid;
		this.antalKm = antalKm;
		
		validator.angivInformationer(startDestination, slutDestination, koersel);
		
		if (validator.validerInformationer() == true) {

			System.out.println("den er true, du har rigtige infoer");

			// TODO returtype skal v�re en form for besked.
		} else{
			throw  new InvalidInformationException(Beskeder.VALIDERINGSFEJL.getDescription());
		}
			
	}

	@Override
	public boolean validerOplysninger(StartDestination startDestination, SlutDestination slutDestination, Date dato,
			int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Beskeder accepterPris() {
		Beskeder besked = null;
		KoerselsKartotek koerselskartotek = new KoerselsKartotekImpl();
		koerselskartotek.gemKoersel(startdestination, slutdestination, koersel, tid, antalKm);
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

	public void getPrisTilbud(StartDestination startdestination, SlutDestination slutdestination, double km)
			throws SQLException, UnknownKommuneException {
		pb = new PrisBeregnerImpl(startdestination, slutdestination, km);
		pb.addObserver(this);
		(new Thread(pb)).start(); // TODO REFACTOR

	}

	public double getPris() {
		return pris;

	}

	public void setPris(double pris) {
		this.pris = pris;

	}

};
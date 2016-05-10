package logic;

import java.sql.SQLException;
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
import domain.StartDestination;
import persistence.ProfilKartotekImpl;

public class FTPControllerImpl extends Observable implements FTPController,Observer {

	PrisBeregnerImpl pb;
	double pris;
	Tilstande tilstand;

	@Override
	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination, LocalDate dato,
			int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer, int brugerNummer) {
		KoerselImpl koersel = new KoerselImpl();
		Validator validator = new ValidatorImpl();
		koersel.setAntalBagage(antalBagage);
		koersel.setAntalPersoner(antalPersoner);
		koersel.setBrugerNummer(brugerNummer);
		koersel.setDato(dato);
		
		
		koersel.setHjaelplemidler(antalHjaelpemidler);
		koersel.setKommentar(kommentarer);
		
		validator.angivInformationer(startDestination, slutDestination, koersel);
		
		if(validator.validerInformationer() == true){
			
			
			PrisBeregnerImpl prisberegner = new PrisBeregnerImpl(startDestination, slutDestination);
			pb = prisberegner;
			pb.addObserver(this);
			(new Thread(pb)).start(); //TODO REFACTOR
			
			
			tilstand = Tilstande.BEREGNER;
			while(tilstand == Tilstande.BEREGNER){
				if(tilstand == Tilstande.BEREGNET){
					break;
				}
			}
		}
		return pris;
	}

	@Override
	public boolean validerOplysninger(StartDestination startDestination, SlutDestination slutDestination, Date dato,
			int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accepterPris() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Profil anmodOmProfil(double kundeNummer) throws SQLException {
		ProfilKartotekImpl profilkartotek = new ProfilKartotekImpl();
		Profil profil = new ProfilImpl(); 
		profil=profilkartotek.anmodOmProfil(kundeNummer);
		return profil;
	}

	@Override
	public void update(Observable o, Object arg) {
		/*if (pb.getTilstand()== Tilstande.BEREGNER){
			tilstand = Tilstande.BEREGNER;
		} else if(pb.getTilstand() == Tilstande.BEREGNET){*/
			pris = pb.getPris();
			tilstand = Tilstande.BEREGNET;
			
		//}
		
	}

}

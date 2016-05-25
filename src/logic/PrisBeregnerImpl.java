package logic;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import domain.SlutDestination;
import domain.StartDestination;
import persistence.KommuneKartotekImpl;
import sats.Sats;
import sats.UnknownKommuneException;

public class PrisBeregnerImpl extends Observable implements PrisBeregner {

	public PrisBeregnerImpl(StartDestination startDestination, SlutDestination slutDestination, double km, Date dato) {
		this.startDestination = startDestination;
		this.slutDestination = slutDestination;
		this.km = km;
		this.dato = dato;

	}

	Tilstande tilstand;
	StartDestination startDestination;
	SlutDestination slutDestination;
	Date dato;

	double pris;
	double sats;
	double km;

	@Override
	public void run() {

		tilstand = Tilstande.BEREGNER;

		

				try {
					pris = beregnPris(startDestination, slutDestination,dato);
				} catch (UnknownKommuneException | SQLException e) {
					
					e.printStackTrace();
				} catch (NullPointerException e){
					tilstand = Tilstande.BEREGNINGSFEJL;
					setChanged();
					notifyObservers();
				}

			
		tilstand = Tilstande.BEREGNET;

	}

	@Override
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination, Date dato)
			throws UnknownKommuneException, SQLException{
		KommuneKartotekImpl kk = new KommuneKartotekImpl();
		
		String startKommune = kk.postnummerTilKommune(startDestination.getPostnummer());
		
			
		
		startKommune = startKommune.replace(" Kommune", "");
		startKommune = startKommune.replace("-", "/");
		String slutKommune = kk.postnummerTilKommune(slutDestination.getPostnummer());
		slutKommune = slutKommune.replace(" Kommune", "");
		slutKommune = slutKommune.replace("-", "/");

		Sats s = Sats.i();
		tilstand = Tilstande.BEREGNET;

		sats = s.getSats(startKommune, slutKommune, dato.getYear(),dato.getMonth(),dato.getDay());
		setChanged();
		notifyObservers();

		return sats;
	}

	@Override
	public double getPris() {

		return sats * km;
	}

	@Override
	public Tilstande getTilstand() {

		return tilstand;
	}

	@Override
	public double getPris(double antalKm) {
		// TODO HER SES EKSEMPEL PÅ POLIMORFI
		return antalKm * sats;
	}

}

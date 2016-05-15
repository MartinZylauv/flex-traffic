package logic;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import domain.SlutDestination;
import domain.StartDestination;
import persistence.KommuneKartotekImpl;
import sats.Sats;
import sats.UnknownKommuneException;

public class PrisBeregnerImpl extends Observable implements PrisBeregner {

	public PrisBeregnerImpl(StartDestination startDestination, SlutDestination slutDestination, double km) {
		this.startDestination = startDestination;
		this.slutDestination = slutDestination;
		this.km = km;

	}

	Tilstande tilstand;
	StartDestination startDestination;
	SlutDestination slutDestination;

	double pris;
	double sats;
	double km;

	@Override
	public void run() {

		tilstand = Tilstande.BEREGNER;

		try {
			try {
				pris = beregnPris(startDestination, slutDestination);
			} catch (SQLException e) { // TODO fix lige med med multi-catch
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownKommuneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tilstand = Tilstande.BEREGNET;

	}

	@Override
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination)
			throws UnknownKommuneException, SQLException {
		KommuneKartotekImpl kk = new KommuneKartotekImpl();
		String startKommune = kk.postnummerTilKommune(startDestination.getPostnummer());
		startKommune = startKommune.replace(" Kommune", "");
		startKommune = startKommune.replace("-", "/");
		String slutKommune = kk.postnummerTilKommune(slutDestination.getPostnummer());
		slutKommune = slutKommune.replace(" Kommune", "");
		slutKommune = slutKommune.replace("-", "/");

		Sats s = Sats.i();
		tilstand = Tilstande.BEREGNET;

		sats = s.getSats(startKommune, slutKommune, 2017, 07, 07);
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

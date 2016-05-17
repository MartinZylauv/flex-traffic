package logic;

import java.sql.Date;
import java.sql.SQLException;

import domain.SlutDestination;
import domain.StartDestination;
import sats.UnknownKommuneException;

public interface PrisBeregner extends Runnable {

	@Override
	public void run();

	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination, Date dato)
			throws UnknownKommuneException, SQLException;

	public double getPris(double antalKm);

	double getPris();

	Tilstande getTilstand();
}

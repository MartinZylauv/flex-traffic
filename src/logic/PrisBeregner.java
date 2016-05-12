package logic;

import java.sql.SQLException;

import domain.Koersel;
import domain.SlutDestination;
import domain.StartDestination;
import sats.UnknownKommuneException;

public interface PrisBeregner extends Runnable {


	
	@Override
	public void run();
	
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination) throws UnknownKommuneException, SQLException;
	
	public double getPris(double antalKm);

	double getPris();
}

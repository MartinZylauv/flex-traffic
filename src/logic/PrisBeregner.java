package logic;

import domain.SlutDestination;
import domain.StartDestination;
import sats.UnknownKommuneException;

public interface PrisBeregner extends Runnable {


	
	@Override
	public void run();
	
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination) throws UnknownKommuneException;
	
	public double getPris();
}

package logic;

import domain.SlutDestination;
import domain.StartDestination;

public interface PrisBeregner extends Runnable {


	
	@Override
	public void run();
	
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination);
	
	public double getPris();
}

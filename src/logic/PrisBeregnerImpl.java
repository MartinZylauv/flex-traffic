package logic;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import domain.SlutDestination;
import domain.StartDestination;


public class PrisBeregnerImpl extends Observable implements PrisBeregner  {

	public PrisBeregnerImpl(StartDestination startDestination, SlutDestination slutDestination) {
		this.startDestination = startDestination;
		this.slutDestination = slutDestination;
		

	}
	
	
	
	Tilstande tilstand;
	StartDestination startDestination;
	SlutDestination slutDestination;
	
	double pris;

	

	
	
	@Override
	public void run() {
		tilstand = Tilstande.BEREGNER;
		setChanged();
		notifyObservers();
		
		
		pris = beregnPris(startDestination, slutDestination);
		tilstand = Tilstande.BEREGNET;
		setChanged();
		notifyObservers();
	}

	@Override
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination) {
		System.out.println(startDestination.getAdresse() + startDestination.getBynavn()+startDestination.getPostnummer());
		System.out.println(tilstand);
		return 0;
	}

	@Override
	public double getPris() {
		
		return pris;
	}
	
	public Tilstande getTilstand(){
		
		return tilstand;
	}



	
	
	



}

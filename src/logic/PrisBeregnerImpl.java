package logic;

import java.lang.reflect.Method;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;


import domain.SlutDestination;
import domain.StartDestination;
import sats.Sats;
import sats.UnknownKommuneException;


public class PrisBeregnerImpl extends Observable implements PrisBeregner  {

	public PrisBeregnerImpl(StartDestination startDestination, SlutDestination slutDestination) {
		this.startDestination = startDestination;
		this.slutDestination = slutDestination;
		

	}
	
	
	
	Tilstande tilstand;
	StartDestination startDestination;
	SlutDestination slutDestination;
	
	double pris;
	double sats;

	

	
	
	@Override
	public void run() {
		tilstand = Tilstande.BEREGNER;
		setChanged();
		notifyObservers();
		
		
		try {
			pris = beregnPris(startDestination, slutDestination);
		} catch (UnknownKommuneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tilstand = Tilstande.BEREGNET;
		setChanged();
		notifyObservers();
	}

	@Override
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination) throws UnknownKommuneException {
		System.out.println(startDestination.getAdresse() + startDestination.getBynavn()+startDestination.getPostnummer());
		System.out.println(tilstand);
		
		Sats s = Sats.i();
		System.out.println(Arrays.toString(s.getKommuner()));
		System.out.println("sats:"+s.getSats("Aarhus", "Herning", 2017, 07, 07));
		sats = s.getSats("Aarhus", "Herning", 2017, 07, 07);
		//s.getSats(fraKommune, tilKommune, year, month, day);
		
		
		
		return 0;
	}

	@Override
	public double getPris(double antalKm) {
		
		return sats*antalKm;
	}
	
	public Tilstande getTilstand(){
		
		return tilstand;
	}



	
	
	



}

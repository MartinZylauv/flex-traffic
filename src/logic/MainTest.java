package logic;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Calendar;

import domain.Profil;
import domain.ProfilImpl;
import domain.SlutDestination;
import domain.SlutDestinationImpl;
import domain.StartDestination;
import domain.StartDestinationImpl;
import persistence.KoerselsKartotekImpl;

public class MainTest {

	public static void main(String[] args) throws SQLException {
		FTPController t = new FTPControllerImpl();
		
		KoerselsKartotekImpl k = new KoerselsKartotekImpl();
		Date.valueOf(LocalDate.of(1995,01, 01));
		System.out.println(k.visEnkeltBrugerKørslerTidsinterval(123, Date.valueOf(LocalDate.of(1995,01, 01)),Date.valueOf(LocalDate.of(2020,01, 01))).toString());
		System.out.println(k.visEnkeltBrugerKørsler(123));
		System.out.println(k.visFlereBrugerKørslerTidsinterval(Date.valueOf(LocalDate.of(2016, 01, 01)),Date.valueOf(LocalDate.of(2016, 10, 30))));
		System.out.println(k.visFlereBrugerKørsler());
		System.out.println(t.anmodOmBrugeresKørselHistorik(0, Date.valueOf(LocalDate.of(2016, 01, 01)),Date.valueOf(LocalDate.of(2016, 10, 30))));
		
		
		/*
		Time t1 = new Time(23,25,00);
		Time t2 = new Time(System.currentTimeMillis());
		
		System.currentTimeMillis();
		if(t1.compareTo(t2)< 0){
			System.out.println("hej");
		}
		System.out.println();
		
		FTPController t = new FTPControllerImpl();
			
		
		StartDestination sta = new StartDestinationImpl();
		SlutDestination slu = new SlutDestinationImpl();
		sta.setAdresse("test");
		sta.setBynavn("test");
		sta.setDato(LocalDate.of(2017, Month.JANUARY, 01));
		sta.setPostnummer(7430);
		slu.setAdresse("test");
		slu.setBynavn("test");
		slu.setDato(LocalDate.of(2017, Month.JANUARY, 01));
		slu.setPostnummer(7430);
		
		Time tuds = new Time (10,10,00);
		
		//t.angivInformationer(sta, slu, LocalDate.of(2016, Month.MAY, 11), 5, 3, 3, "hej din so", 10, tuds, 20);
		Profil p = new ProfilImpl();
		p = t.anmodOmProfil(0001);
		System.out.println(p.toString());
		System.out.println(p.getFuldtNavn());
		System.out.println(p.getEmail());
		System.out.println(p.getKundeNummer());
		System.out.println(p.getTlfNummer());
*/
		
		//TODO denne klasse er unødvendig og burde slettes inden programmet er færdig.
	}

}

package logic;

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

public class MainTest {

	public static void main(String[] args) throws SQLException {
		
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
		
		t.angivInformationer(sta, slu, LocalDate.of(2016, Month.MAY, 11), 5, 3, 3, "hej din so", 10, tuds, 20);
		Profil p = new ProfilImpl();
		p = t.anmodOmProfil(0001);
		System.out.println(p.toString());
		System.out.println(p.getFuldtNavn());
		System.out.println(p.getEmail());
		System.out.println(p.getKundeNummer());
		System.out.println(p.getTlfNummer());

	}

}

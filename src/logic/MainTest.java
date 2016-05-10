package logic;

import java.time.LocalDate;
import java.time.Month;

import domain.SlutDestination;
import domain.SlutDestinationImpl;
import domain.StartDestination;
import domain.StartDestinationImpl;

public class MainTest {

	public static void main(String[] args) {
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
		
		t.angivInformationer(sta, slu, LocalDate.of(2016, Month.MAY, 11), 5, 3, 3, "hej din so", 10);

	}

}

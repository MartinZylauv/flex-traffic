package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import domain.SlutDestinationImpl;
import domain.StartDestinationImpl;
import logic.FTPControllerImpl;
import logic.InvalidInformationException;

public class UC1 {
	
	FTPControllerImpl ftp = new FTPControllerImpl();
	StartDestinationImpl startdest = new StartDestinationImpl();
	SlutDestinationImpl slutdest = new SlutDestinationImpl();

	@Test
	public void testAngivInformationer() {
		//test af dato
		startdest.setAdresse("test");
		startdest.setBynavn("test");
		startdest.setPostnummer(1234); 
		slutdest.setAdresse("test");
		slutdest.setBynavn("test");
		slutdest.setPostnummer(1234);
		try {
			ftp.angivInformationer(startdest, slutdest, Date.valueOf(LocalDate.of(2015, 07, 07)), 1, 1, 1, "", 1, Time.valueOf(LocalTime.now()), 10);
			fail("Exception bliver ikke kastet.");
		} catch (InvalidInformationException e) {
			//success

		}
		
	}

	@Test
	public void testAccepterPris() {
		
	}

	@Test
	public void testGetPrisTilbud() {
		
	}

}

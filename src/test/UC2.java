package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import domain.SlutDestinationImpl;
import domain.StartDestinationImpl;
import logic.FTPControllerImpl;
import logic.InvalidInformationException;
import sats.UnknownKommuneException;

public class UC2 {
	
	FTPControllerImpl ftp = new FTPControllerImpl();
	StartDestinationImpl startdest = new StartDestinationImpl();
	SlutDestinationImpl slutdest = new SlutDestinationImpl();

	@Test
	public void testAngivInformationerDatoFørIDag() {
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
	public void testAngivInformationerPersonerOver11() {
		startdest.setAdresse("test");
		startdest.setBynavn("test");
		startdest.setPostnummer(1234); 
		slutdest.setAdresse("test");
		slutdest.setBynavn("test");
		slutdest.setPostnummer(1234);
		try {
			ftp.angivInformationer(startdest, slutdest, Date.valueOf(LocalDate.of(2017, 07, 07)), 11, 1, 1, "", 1, Time.valueOf(LocalTime.now()), 10);
			fail("Exception bliver ikke kastet.");
		} catch (InvalidInformationException e) {
			//success

		}
		
	}
	
	@Test
	public void testAngivInformationerRigtig() {
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
	public void testGetPrisTilbudValid() {
		startdest.setAdresse("test");
		startdest.setBynavn("test");
		startdest.setPostnummer(1234); 
		slutdest.setAdresse("test");
		slutdest.setBynavn("test");
		slutdest.setPostnummer(1234);
		try {
			ftp.getPrisTilbud(startdest, slutdest, 20, Date.valueOf(LocalDate.of(2017, 07, 07)));
			//success
		} catch (SQLException e) {
			fail("SQLException");
		} catch (UnknownKommuneException e) {
			fail("unknowkommune");
		} catch (InvalidInformationException e) {
			fail("invalid information");
		}
	}
	
	@Test
	public void testGetPrisTilbudTomStartAdr() {
		startdest.setAdresse("");
		startdest.setBynavn("test");
		startdest.setPostnummer(1234); 
		slutdest.setAdresse("test");
		slutdest.setBynavn("test");
		slutdest.setPostnummer(1234);
		try {
			ftp.getPrisTilbud(startdest, slutdest, 20, Date.valueOf(LocalDate.of(2017, 07, 07)));
			fail("ingen exeption kastet");
		} catch (SQLException e) {
			fail("SQLException");
		} catch (UnknownKommuneException e) {
			fail("unknowkommune");
		} catch (InvalidInformationException e) {
			//success
		}
	}
	
	@Test
	public void testGetPrisTilbudTomByNavn() {
		startdest.setAdresse("test");
		startdest.setBynavn("");
		startdest.setPostnummer(1234); 
		slutdest.setAdresse("test");
		slutdest.setBynavn("test");
		slutdest.setPostnummer(1234);
		try {
			ftp.getPrisTilbud(startdest, slutdest, 20, Date.valueOf(LocalDate.of(2017, 07, 07)));
			fail("ingen exeption kastet");
		} catch (SQLException e) {
			fail("SQLException");
		} catch (UnknownKommuneException e) {
			fail("unknowkommune");
		} catch (InvalidInformationException e) {
			//success
		}
	}
	
	//TODO: snak om test med anders evt.

}

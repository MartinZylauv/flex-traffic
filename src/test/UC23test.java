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

public class UC23test {
	
	FTPControllerImpl ftp = new FTPControllerImpl();


	//@Test ude af drift da den sidste test tester om der kan redigeres, det kan der. Denne test her gik dog rent igennem.
	public void testAnmodOmProfilKundenummer1() {
		ftp.setKundenummer(1);
		
			try {
				assertEquals("010199-4567", ftp.anmodOmProfil(ftp.getKundenummer()).getCPR());
				assertEquals("Email2", ftp.anmodOmProfil(ftp.getKundenummer()).getEmail());
				assertEquals("Fulde navn2", ftp.anmodOmProfil(ftp.getKundenummer()).getFuldtNavn());
				assertEquals(12345678, ftp.anmodOmProfil(ftp.getKundenummer()).getTlfNummer());
				
			} catch (SQLException e) {
				fail("SQLException");
			}
		
	}
	
	@Test
	public void testAnmodOmProfilKundenummer123() {
		
	
		ftp.setKundenummer(123);
		
		try {
			assertEquals("010199-1234", ftp.anmodOmProfil(ftp.getKundenummer()).getCPR());
			assertEquals("marti", ftp.anmodOmProfil(ftp.getKundenummer()).getEmail());
			assertEquals("Martin Zylauv Kristensen", ftp.anmodOmProfil(ftp.getKundenummer()).getFuldtNavn());
			assertEquals(61676140, ftp.anmodOmProfil(ftp.getKundenummer()).getTlfNummer());
			
		} catch (SQLException e) {
			fail("SQLException");
		}
		}
	
	
		
		
	
	
	@Test
	public void testRedigerProfil() {
		ftp.setKundenummer(1);
		try {
			ftp.indtastNyeInformationer("Steffen", "Email", 12346789);
			ftp.anmodOmProfil(ftp.getKundenummer());
		
			assertEquals("Email", ftp.anmodOmProfil(ftp.getKundenummer()).getEmail());
			assertEquals("Steffen", ftp.anmodOmProfil(ftp.getKundenummer()).getFuldtNavn());
			assertEquals(12346789, ftp.anmodOmProfil(ftp.getKundenummer()).getTlfNummer());
		} catch (SQLException e) {
			fail();
			e.printStackTrace();
		} catch (InvalidInformationException e) {
			fail();
			e.printStackTrace();
		}
		
	}



	}
	



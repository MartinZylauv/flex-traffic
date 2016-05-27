package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Test;

import domain.SlutDestinationImpl;
import domain.StartDestinationImpl;
import logic.PrisBeregnerImpl;
import sats.UnknownKommuneException;

public class PrisberegnerTest {

	@Test
	public void TestRigtigInfo() {
		StartDestinationImpl start = new StartDestinationImpl();
		start.setAdresse("test");
		start.setBynavn("test");
		start.setPostnummer(7400);
		SlutDestinationImpl slut = new SlutDestinationImpl();
		slut.setAdresse("test");
		slut.setBynavn("test");
		slut.setPostnummer(7400);
		
		double km = 1;
		Date d = Date.valueOf(LocalDate.of(2016, 12, 12));
		PrisBeregnerImpl pb = new PrisBeregnerImpl(start, slut, km, d);
		try {
			pb.beregnPris(start, slut, d);
			assertEquals(5.0, pb.beregnPris(start, slut, d),0.01);
		} catch (UnknownKommuneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void TestUnknownKommune() {
		StartDestinationImpl start = new StartDestinationImpl();
		start.setAdresse("test");
		start.setBynavn("test");
		start.setPostnummer(2100);
		SlutDestinationImpl slut = new SlutDestinationImpl();
		slut.setAdresse("test");
		slut.setBynavn("test");
		slut.setPostnummer(7400);
		
		double km = 1;
		Date d = Date.valueOf(LocalDate.of(2016, 12, 12));
		PrisBeregnerImpl pb = new PrisBeregnerImpl(start, slut, km, d);
		try {
			pb.beregnPris(start, slut, d);
			fail("ingen exception");
		} catch (UnknownKommuneException e) {
			//success
			e.printStackTrace();
		} catch (SQLException e) {
			fail("Forkert exception");
			e.printStackTrace();
		}
	}
	
	//@Test
	public void TestKommuneIkkeIOpslags() {
		StartDestinationImpl start = new StartDestinationImpl();
		start.setAdresse("test");
		start.setBynavn("test");
		start.setPostnummer(1234);
		SlutDestinationImpl slut = new SlutDestinationImpl();
		slut.setAdresse("test");
		slut.setBynavn("test");
		slut.setPostnummer(7400);
		
		double km = 1;
		Date d = Date.valueOf(LocalDate.of(2016, 12, 12));
		PrisBeregnerImpl pb = new PrisBeregnerImpl(start, slut, km, d);
		try {
			pb.beregnPris(start, slut, d);
			fail("ingen exception");
		} catch (UnknownKommuneException e) {
			
			fail("Forkert exception");
		} catch (SQLException e) {
			fail();
			
		} catch (NullPointerException e){
			//success
		}
	}

}

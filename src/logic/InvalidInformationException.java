package logic;

public class InvalidInformationException extends Exception
{
 //TODO Serial version tjek lige på stackoverflow.
	public InvalidInformationException(String message){
		super(message); //modtager en string der kan bruges i exceptionen.
	}

}

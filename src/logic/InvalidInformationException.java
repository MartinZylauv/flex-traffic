package logic;

public class InvalidInformationException extends Exception
{
	public InvalidInformationException(String message){
		super(message); //modtager en string der kan bruges i exceptionen.
	}

}

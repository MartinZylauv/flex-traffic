package logic;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import domain.KoerselHistorikImpl;

//TODO: http://stackoverflow.com/questions/3666007/how-to-serialize-object-to-csv-file
public class CSVWriter {
	
    public void writeToCSV(ArrayList<KoerselHistorikImpl> koersel, File file) throws IOException
    {
    	
    	FileWriter writer = new FileWriter(file);
		 
    	writer.append("Dato");
    	writer.append(',');
    	writer.append("Antal personer");
    	writer.append(',');
    	writer.append("Antal hjælpemidler"); //TODO Æ'ET BLIVER IKKE FORMATERET RIGTIT I CSV FILEN DESVÆRRE.
    	writer.append(',');
    	writer.append("Antal bagage");
    	writer.append(',');
    	writer.append("Brugernummer");
    	writer.append(',');
    	writer.append("Kommentar");
    	writer.append(',');
    	writer.append("Tid");
    	writer.append(',');
    	writer.append("Antal kilometer");
    	writer.append(',');
    	writer.append("Pris");
    	writer.append(',');
    	writer.append("Start adresse");
    	writer.append(',');
    	writer.append("Start postnummer");
    	writer.append(',');
    	
    	writer.append("Slut adresse");
    	writer.append(',');
    	writer.append("Slut postnummer");
    	
    	writer.append('\n');
    	for(KoerselHistorikImpl koerselobj : koersel){
    		writer.append(koerselobj.getDato().toString());
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getAntalPersoner()));
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getAntalHjaelpemidler()));
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getAntalBagage()));
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getBrugerNummer()));
        	writer.append(',');
        	writer.append(koerselobj.getKommentar());
        	writer.append(',');
        	writer.append(koerselobj.getTid().toString());
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getAntalKm()));
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getPris()));
        	writer.append(',');
        	writer.append(koerselobj.getStartAdresse());
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getStartPostnummer()));
        	
        	writer.append(',');
        	writer.append(koerselobj.getSlutAdresse());
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getSlutPostnummer()));
        	
        	writer.append('\n');
    	}
    	//TODO eventuelt tilføj start/slut bynavn.
 	    
 	   
 			
 	    writer.flush();
 	    writer.close();
    	
    	
       
        }

    
}


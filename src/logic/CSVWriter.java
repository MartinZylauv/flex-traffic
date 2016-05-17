package logic;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import domain.KoerselHistorikImpl;

//TODO: http://stackoverflow.com/questions/3666007/how-to-serialize-object-to-csv-file
public class CSVWriter {
	private static final String CSV_SEPARATOR = ",";
    public void writeToCSV(ArrayList<KoerselHistorikImpl> koersel, File file) throws IOException
    {
    	//TODO vi skal definere hver coloum name og så gøre som her, easy peasy.
    	FileWriter writer = new FileWriter(file);
		 
    	writer.append("km");
    	writer.append(',');
    	writer.append("pris");
    	writer.append('\n');
    	for(KoerselHistorikImpl koerselobj : koersel){
    		writer.append(String.valueOf(koerselobj.getAntalKm()));
        	writer.append(',');
        	writer.append(String.valueOf(koerselobj.getPris()));	
        	writer.append('\n');
    	}
    	
 	    
 	   
 			
 	    writer.flush();
 	    writer.close();
    	
    	
       
        }

    
}


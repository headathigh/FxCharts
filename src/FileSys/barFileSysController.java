package FileSys;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class barFileSysController {
	static FileWriter locFile = null;
	@SuppressWarnings("null")
	public static void writeDataInFile(ArrayList<String> xVal,Number[] yFin, String lineName, int seriesId) {
		System.out.println("File System Report:\n");
	    try {	    
	    	locFile = new FileWriter("dat/barFileData.txt",true);
	    	Scanner scanner = new Scanner(new FileReader("dat/barFileData.txt"));
	    	if(scanner.hasNextLine()) {
	    		locFile.append("\n");
	    	}
	        //locFile.append(seriesId+"-");
	    	locFile.append("{"+lineName+"}");
	        for(int i=0;i<xVal.size();i++) {
	            locFile.append("["+xVal.get(i)+","+yFin[i]+"]");
	        }	        
	        System.out.println("\tFile Appended");
	    } catch(IOException e) {
	        System.out.println("\tFileWriter Catch block");
	        e.printStackTrace();
	    } finally {
            System.out.println("\tFileWriter Finally block");
            try {if(locFile != null) {locFile.close();}} 
            catch(IOException e) {e.printStackTrace();}
        }
	}
	public static ArrayList<String> readDataFromFile() {
		Scanner scanner = null;
		System.out.println("File System Report:\n");
		ArrayList<String> seriesArr = new ArrayList<String>();
        try {
            scanner = new Scanner(new FileReader("dat/barFileData.txt"));
            scanner.useDelimiter(",");            
            while(scanner.hasNextLine()) {                
                String line = scanner.nextLine();                
                seriesArr.add(line);                
            }
    		System.out.println("\tFile Reading");
        } catch(IOException e) {
            e.printStackTrace();
    		System.out.println("\tFile Reading error");
        } finally {
            if(scanner != null) {
                scanner.close();
            }
    		System.out.println("\tFile Reading finally");
        }
        return seriesArr;
	}
	public static void clearFile(String fileName) {
		System.out.println("File System Report:\n");
		try {
			locFile = new FileWriter("dat/"+fileName,false);
	    	locFile.flush();
	    	locFile.close();
			System.out.println("\tFile Cleared");
		}catch(IOException e) {
			System.out.println("\tFile clearing error");
		}
	}
	/*
	 * public static void main(String[] args) { int xVal[] = {12,3,4,1,5,6,3}; int
	 * yVal[] = {12,3,4,1,5,6,3}; int i=0; String fileName = "locations.txt";
	 * clearFile(fileName); for(i=0;i<3;i++) writeDataInFile(xVal,yVal,fileName,i);
	 * 
	 * readDataFromFile(); }
	 */
}
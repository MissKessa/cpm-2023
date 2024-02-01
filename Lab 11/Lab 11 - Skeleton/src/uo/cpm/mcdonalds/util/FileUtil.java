package uo.cpm.mcdonalds.util;

import java.io.*;
import java.util.*;

import uo.cpm.mcdonalds.model.Product;

public abstract class FileUtil {
	
public static void loadFile (String fileName, List<Product> productsList) {
		
	    String line;
	    String[] productData= null;	   
	    
	    try {
	    	   BufferedReader file = new BufferedReader(new FileReader(fileName));
	    		while (file.ready()) {
	    			line = file.readLine();
	    			productData = line.split("@");
	    			productsList.add(new Product(productData[0],productData[1],productData[2],Float.parseFloat(productData[3]),0));
	    		}
	    		file.close();
	    }
	    catch (FileNotFoundException fnfe) {
	      System.out.println("File not found.");
	    }
	    catch (IOException ioe) {
	      new RuntimeException("I/O Error.");
	    } 
	  }
	
	public static void saveToFile(String fileName, List<Product> orderList ){
		try {
		        BufferedWriter file = new BufferedWriter(new FileWriter("files/" + fileName + ".dat"));
		        String line = orderList.toString();
		        file.write(line);
		        file.close();
			}

		catch (FileNotFoundException fnfe) {
		      System.out.println("The file could not be saved.");
		    }
		catch (IOException ioe) {
		      new RuntimeException("I/O Error.");
		}
	  }
	
	public static String setFileName(){
		String code = "";
		String base = "0123456789abcdefghijklmnopqrstuvwxyz";
		int length = 8;
		for(int i=0; i<length;i++){ 
			int numero = (int)(Math.random()*(base.length())); 
			code += base.charAt(numero);
		}
		return code;
	}
}

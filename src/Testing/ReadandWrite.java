package Testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This program reads input from a file, formats the information into a solicitation email, then prints that information to a file. Unfortunately, I am unable to apply this to my email
 * for full automation.
 * 
 * Limitations
 * 		Input file MUST be in a particular format in order for this to work properly. an example line of input data on the next line..
 * 		TIK#	DESCRIPTION		TECHNICIAN NAME		USER NAME	
 * 		86092	CAT credentials	Brian Tomaszewski	Bill Craft
 * 		
 * 		each entry is separated by a 'tab' spacing. spacing is auto formatted if copied from an excel file to a text file
 * 
 * 		
 * @author alexberthon
 *
 */
public class ReadandWrite {
	public static void main(String[] args) {
		String tikNum = "";
		String descrip = "";
		String name = "";
		String tech = "";		
		String[] format = null;	
		int select = 1;
		
		Scanner in = null;
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		
		try {
			File input = new File("C:\\Users\\AlexBerthon\\eclipse-workspace\\Development\\src\\Testing\\Input.txt");
			in = new Scanner(input);
			fileWriter = new FileWriter("C:\\Users\\AlexBerthon\\eclipse-workspace\\Development\\src\\Testing\\Output.txt");
		    printWriter = new PrintWriter(fileWriter);
			
		}
		catch(FileNotFoundException e) {
			System.err.println("Critical Error: File not found:FileReader");
		}
		catch(IOException j) {
			System.err.println("Critical Error: File not found:FileWriter");
		}
				
		while(in.hasNext()) {
			format = in.nextLine().split("	");
			name = format[0];
			descrip = format[1];

			//Survey Text
			if(select == 0) {
				printWriter.println("Dear "+name+", \n");
				printWriter.println("This is Alex from the IT department. I am contacting you about a recently closed ticket, '"+descrip+"'. If you could take a moment to follow the instructions below and fill out a ticket survey we would really appreciate the feedback.\n");
				printWriter.println("Instructions: ");
				printWriter.println("Step 1: Click this link. https://forms.office.com/Pages/ResponsePage.aspx?id=shXzJUJ0t0GpSFLiBGMKE1BSi1Zul1xFuAy72MJjFrxUOFRRWTk0TzNCNTBMVkQ0TEtQNjZaWVBIRyQlQCN0PWcu");
				printWriter.println("Step 2: Log in with your Outlook account (if prompted)");
				printWriter.println("Step 3: Complete the survey and click submit\n");
				printWriter.println("If you experience any issues or have any questions, please call me on the number below.\n");
				printWriter.println("Thank you!");
			}
			//Trend Update email
			else if(select == 1) {
				printWriter.println("Dear "+name+", \n");
				printWriter.println("Our records indicate that your computer "+descrip+" is running an old version of our Trend Micro security software and needs to be updated as soon as possible. Please contact me by replying to this email or calling me directly at extension 4703 to set up a time for this update.\n");
				printWriter.println("Thank you!");
			}
		}
		
		try {
			fileWriter.close();
		}
		catch(IOException j) {
			System.err.println("error closing? maybe it's still open/being accessed or something");
		}
		
		printWriter.close();
	    in.close();
	}
}

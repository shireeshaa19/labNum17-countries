import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CountryFileApp {
	
	public static void main(String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the Countries Maintenance Application! \n");
		
	      Path path = Paths.get("countries.txt");
	      if(Files.notExists(path)){
	    	  try{
		  Files.createFile(path);
	   } catch (IOException e) {
		   System.out.println("IOException");
	   }
	      }
	      
	      boolean mainMenu = true;
	      
	      do{
	       System.out.println("Main menu\n1. See the list of countries\n2. Add the country\n3. Exit\n");
          System.out.println("Enter menu number: \n");
          int UserChoice = scnr.nextInt();
          scnr.nextLine();
          
          switch(UserChoice)  {
          case 1:
          
          List<Country> countries = CountryFileUtil.readFile();
			for (Country c : countries) {
				System.out.println(c.getName() + " has a population of " + c.getPopulation() );
          
          }
          break;
          case 2:
        	  do{
           	System.out.print("Enter country:  ");
		    String name = scnr.nextLine();
		    System.out.print("Enter their Population number: ");
		     int population = scnr.nextInt();
		    Country newCountry = new Country(name, population);
		
		    try{
		    CountryFileUtil.appendToFile(newCountry);
		
		    } catch (IOException ex) {
		    	System.out.println( "Cannot edit the file");
		    }
        	  }while (doAgain(scnr , "would like to add another country(y/n)? "));
        	  
        	  break;
        	  
          case 3:
        	  
		   System.out.println("Goodbye!");
		   scnr.close();
		   mainMenu = false;
		   break;
		 
	}
} while(mainMenu) ;
	}

	private static boolean doAgain(Scanner scnr, String prompt) {
		
		boolean isValid = false;
		boolean decision = false;
		do {
			System.out.println(prompt);
			String userInput = scnr.nextLine();
			if (userInput.matches("[yYnN][eEoO]{0,1}[sS]{0,1}")) {
				isValid = true;
				if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
					decision = true;
					isValid = true;
				} else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
					decision = false;
					isValid = true;
				}
			} else {
				isValid = false;
				System.out.println("\"" + userInput + "\""
						+ " is not a valid option. You can type \"Y\" or \"Yes\" to continue or  \"N\" and \"No\" to exit");
			}
			// System.out.println(isValid);
		} while (!isValid);

		return decision;
	
	}
}
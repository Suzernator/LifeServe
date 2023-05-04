import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

/**************************************************************
* Name        : Final Project
* Author      : Suzette Senephansiri
* Created     : 05/04/2023
* Course      : CIS 152 Data Structures
* Version     : 1.0
* OS          : Windows 10
* IDE		  : eclipse 2022-12
* Copyright   : This is my own original work based on
*               specifications issued by our instructor
* Description : Final Semester project. ADD, SEARCH, EDIT, DELETE, VIEW hospital data.
*               Input:  User makes selection from menu, inputs appropriate data.
*               Output: Hospital data.
* Academic Honesty: I attest that this is my original work.
* I have not used unauthorized source code, either modified or 
* unmodified. I have not given other fellow student(s) access to
* my program.         
***************************************************************/

public class Hospitals { //Hospital class 
	private static Scanner input = new Scanner(System.in);
	private static HashMap<Integer, String[]> hospitalMap = new HashMap<Integer, String[]>();
	
	public Hospitals() { //default, no-arg constructor
		super();
	}

	public static String calculateExpirationDate(LocalDate currentDate, String days) { //method to find expiration date
		int numOfDays = Integer.parseInt(days);  //change string days to int
	    LocalDate expirationDate = currentDate.plusDays(numOfDays); //get current date, add number of days
	    Date date = java.sql.Date.valueOf(expirationDate); //convert to date obj
	    SimpleDateFormat sdf = new SimpleDateFormat("E, MM/dd/yyyy"); //formatting date
	    String formattedDate = sdf.format(date); //apply format
	    return formattedDate; //return expiry date
	  }
	
	public static void addHospital() { //method to add a hospital
		System.out.print("Enter 4-digit hospital code (ex. 0001): "); //input code
		int newCode = input.nextInt();
		input.nextLine();
		
		System.out.print("Name: "); 
		String name = input.nextLine(); //input name
		if(name.isEmpty()) {
			name = "no data provided";
		}
		
		System.out.print("Street Address: ");
		String address = input.nextLine(); //input street address
		if(address.isEmpty()) {
			address = "no data provided";
		}
		
		System.out.print("City: ");
		String city = input.nextLine(); //input city
		if(city.isEmpty()) {
			city = "unk";
		}
		
		System.out.print("State: ");
		String state = input.nextLine(); //input state
		if(state.isEmpty()) {
			state = "unk";
		}
		
		System.out.print("Zipcode: ");
		String zip = input.nextLine(); //input zip
		if(zip.isEmpty()) {
			zip = "unk";
		}
		
		System.out.print("Phone Number with Area Code (ex. 515-123-4567): ");
		String phone = input.nextLine(); //input phone
		if(phone.isEmpty()) {
			phone = "no data provided";
		}
		
		System.out.print("Fax Number with Area Code (ex. 515-123-4567): ");
		String fax = input.nextLine(); //input fax
		if(fax.isEmpty()) {
			fax = "no data provided";
		}
		
		//menu shows how to convert weeks to days
		System.out.println("\n\t  Outdate calculator for \n\t  minimum number of DAYS \n\t   hospital will accept:");
		System.out.println("\t**************************");
		System.out.println("\t* Shortest date = 0 days *");
		System.out.println("\t*  1 week out =  7 days  *");
		System.out.println("\t*  2 week out = 14 days  *");
		System.out.println("\t*  3 week out = 21 days  *");
		System.out.println("\t*  4 week out = 28 days  *");
		System.out.println("\t**************************");
		
		System.out.print("\nMinimum Outdate (DAYS): ");
		int days = input.nextInt(); //input days

		String[] newInfo = {name, address, city, state, zip, phone, fax, Integer.toString(days)}; //new hospital info set to array
		hospitalMap.put(newCode, newInfo); //adding key and value info to map
		System.out.println("\nNew hospital successfully added!\n");
		
		menu(); //recall menu
	}
	
	public static void searchHospital() { //method to search hospital
		LocalDate today = LocalDate.now(); //get current date
		
		System.out.print("Enter 4-digit hospital code (ex. 0001): ");
		int code = input.nextInt();
		
		if(hospitalMap.containsKey(code)) {
			String[] hospitalInfo = hospitalMap.get(code);

			System.out.println("Name:            " + hospitalInfo[0]);
			System.out.println("Address:         " + hospitalInfo[1] + "\n\t\t " + hospitalInfo[2] + ", " + hospitalInfo[3] + ", " + hospitalInfo[4]);
			System.out.println("Phone Number:    " + hospitalInfo[5]);
			System.out.println("Fax Number:      " + hospitalInfo[6]);
			System.out.println("Minimum Days:    " + hospitalInfo[7]);
			System.out.println("Minimum Expiration date to send: " + calculateExpirationDate(today, hospitalInfo[7]) + "\n");
		}
		
		else {
			System.out.print("Hospital not found.\n\n");
			menu();
		}
		
		menu();
	}
	
	public static void editHospital() { //method to edit info
		System.out.print("Enter 4-digit hospital code (ex. 0001): ");
		int code = input.nextInt(); //input code
		input.nextLine();
		
		if(hospitalMap.containsKey(code)) { //check to see if map key is valid
			String[] editInfo = hospitalMap.get(code); //create new array to hold edited info
			
			System.out.print("Name: ");
			String newName = input.nextLine(); //input new name
			if(!newName.equals("")) { //if no changes made, keep previous input
				editInfo[0] = newName;
			}
			
			System.out.print("Street Address: ");
			String newAddress = input.nextLine(); //input new address
			if(!newAddress.equals("")) {
				editInfo[1] = newAddress;
			}
			
			System.out.print("City: ");
			String newCity = input.nextLine(); //input new city
			if(!newCity.equals("")) {
				editInfo[2] = newCity;
			}
			
			System.out.print("State: ");
			String newState = input.nextLine(); //input new state
			if(!newState.equals("")) {
				editInfo[3] = newState;
			}
			
			System.out.print("Zipcode: ");
			String newZip = input.nextLine(); //input new zip
			if(!newZip.equals("")) {
				editInfo[4] = newZip;
			}
			
			System.out.print("Phone Number with Area Code: ");
			String newPhone = input.nextLine(); //input new phone
			if(!newPhone.equals("")) {
				editInfo[5] = newPhone;
			}
			
			System.out.print("Fax Number with Area Code: ");
			String newFax = input.nextLine(); //input new fax
			if(!newFax.equals("")) {
				editInfo[6] = newFax;
			}
			
			System.out.print("Minimum Outdate (Days): ");
			String newDays = input.nextLine(); //input new num of days
			if(!newDays.equals("")) {
				editInfo[7] = newDays;
			}
			
			hospitalMap.put(code, editInfo); //add edited info value and pair with key, in map
			
			System.out.println("\nHospital information successfully changed!\n");
		}
				
		else {
			System.out.print("Hospital not found.\n\n"); //prompt if hospital code not found
			menu();
		}
		
		menu();
	}
	
	public static void deleteHospital() { //method to delete hospital from map
		System.out.print("Enter 4-digit hospital code (ex. 0001): "); 
		int code = input.nextInt(); //input code
		input.nextLine();
		
		if(hospitalMap.containsKey(code)) { //check to see if code is valid
			String[] hospitalInfo = hospitalMap.get(code); 
			hospitalMap.remove(code, hospitalInfo); //remove hospital key and value from map
			System.out.println("Hospital successfully deleted!\n");
		}
		else { //prompt user if no key is found
			System.out.println("No hospital with that code. No action completed.\n");
		}
		
		menu(); //recall menu
	}
	
	public static void viewHospital() { //method to view all hospitals entered
		LocalDate today = LocalDate.now(); //current date
		
		//printing all key and value from map
		hospitalMap.forEach((k,v) -> {
	        System.out.println("Hospital Code:  " + k);
	        System.out.println("Name:    " + v[0]);
	        System.out.println("Address: " + v[1]);
	        System.out.println("         " + v[2] + ", " + v[3] + ", " + v[4]);
	        System.out.println("Phone:   " + v[5]);
	        System.out.println("Fax:     " + v[6]);
	        System.out.println("Outdate: " + v[7]);
	        System.out.println("Minimum Expiration Date to Send: " + calculateExpirationDate(today, v[7]) + "\n");
		});
		
		if(hospitalMap.isEmpty()) { //prompt user if map is empty
			System.out.println("No current hospitals in database. Please ADD a new hospital first.\n");
			menu();
		}
		
		menu(); //recall menu
	}
	
	public static void menu() { //main menu method
		Scanner sc = new Scanner(System.in);
		
		System.out.println("//Please select a NUMBER from the menu below.\n");
		
		System.out.println("1. ADD new hospital information");
		System.out.println("2. SEARCH for hospital via code");
		System.out.println("3. EDIT hospital information");
		System.out.println("4. DELETE hospital");
		System.out.println("5. VIEW all hospitals");
		System.out.println("6. QUIT program");
		
		System.out.print("\nUser selection: ");
		int selection = sc.nextInt(); //make sure selection is int
		System.out.print("\n");
	
		if((selection > 6) || (selection <= 0)) { //prompt if number is out of bounds
			System.out.println("Please only choose a number from 1-6\n");
			menu();
		}
		
		while((selection > 0) && (selection <= 6)) { //switch case for menu user selection
			switch(selection) {
			
			case 1: 
				addHospital();
				break;
			case 2: 
				searchHospital();
				break;
			case 3:
				editHospital();
				break;
			case 4: 
				deleteHospital();
				break;				
			case 5: 
				viewHospital();
				break;
			case 6: 
				System.out.print("Exiting program, goodbye.");
				System.exit(0);
			default: 
				System.out.print("I'm sorry, that was an invalid selection. Try again.");
				menu();
				}
			}
		
		sc.close();
	}

	public class Main{ //driver-tester class
		
		public static void main(String[] args) {			
			//hard-coding a couple local hospitals to test data
			String[] mercyInfo= {"MercyOne DSM", "1111 6th Ave", "Des Moines", "IA", "50314", "515-247-4480", "515-643-5836", "0"} ; 
			String[] immcInfo = {"Methodist", "1200 Pleasant St", "Des Moines", "IA", "50309", "515-241-6856", "515-241-6898", "0"}; 
			int mercyKey = 25;
			int immcKey = 26;
			
			hospitalMap.put(mercyKey, mercyInfo); //test data
			hospitalMap.put(immcKey, immcInfo); //test data
			
			menu(); //call menu to start program
		}
	}
}

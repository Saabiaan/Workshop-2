package workshop2.view;

import java.io.*;
import java.util.*;

import workshop2.model.*;

public class OutputToUser {
	private MemberRegistry currRegistry = new MemberRegistry();;
	
	private int getUserInputNumber() {
		Scanner inputInt = new Scanner(System.in);
		int userInput = inputInt.nextInt();
			
		while (userInput == '\r' || userInput =='\n')
			userInput = inputInt.nextInt();
			
		return userInput;
	}
	
	private String getUserInputText() {
		Scanner inputText = new Scanner(System.in);
		String userInput = inputText.nextLine();
		
		return userInput;
	}
	
	public void showMenu() {
		System.out.println("\n\n\nHello and welcome to BoatRegistry 2000!\n");
		System.out.println("What would you like to do:\n");
		System.out.println("1. Add user.");
		System.out.println("2. Remove user.");
		System.out.println("3. Search user.");
		System.out.println("4. Update user.");
		System.out.println("5. Register boat to user.");
		System.out.println("6. Change boat info.");
		System.out.println("7. Remove boat info.");
		System.out.println("8. View compact list.");
		System.out.println("9. View verbose list.");
		System.out.print("Make your choice by entering the number of the task you would like to do: ");
		while (true) {
			switch (getUserInputNumber()) {
				case 1: 
					System.out.println("       ---Add member---     ");
					showAddMember();
					break;
				case 2:
					System.out.println("       ---Remove member---     ");
					showRemoveMember();
					break;
				case 3: 
					System.out.println("       ---Search for member---     ");
					showSearchMember();
					break;
				case 4: 
					System.out.println("       ---Update member---     ");
					showUpdateMember();
					break;
				case 5:
					System.out.println("       ---Register boat---     ");
					showRegisterBoat();
					break;
				case 6: 
					System.out.println("       ---Change boat---     ");
					showChangeBoat();
					break;
				case 7: 
					System.out.println("       ---Remove boat---     ");
					showRemoveBoat();
					break;
				case 8: 
					System.out.println("     ---Compact list---     ");
					showCompactList();
					break;
				case 9: 
					System.out.println("     ---Verbose list---     ");
					showVerboseList();
					break;
				default:
					System.out.println("You must type an integer between 1 and 9!");
					break;
			}
		}
			
	}
	
	private void showCompactList() {	
		for(Member member : currRegistry.getMemberList())
			System.out.println("Name: " + member.getName() + "\nMember ID: " + member.getID() + "\nNumber of boats: " + member.getBoats().size());
		showMenu();
	}
	
	private void showVerboseList() {
		for(Member member : currRegistry.getMemberList()) 
			searchForMember(member.getID());
		showMenu();
	}
	
	private void showAddMember() {
		System.out.print("Name: ");
		String name = getUserInputText();
		System.out.print("Personal Number (format yymmdd): ");
		int persNr = getUserInputNumber();
		//Spara till register
		currRegistry.addMember(name, persNr);
		showMenu();
	}
	
	private void showRemoveMember() {
		System.out.print("Please enter the members ID (integers only): ");
		int id = getUserInputNumber();
		validateID(id);
		
		currRegistry.removeMember(id);
		showMenu();
	}
	
	private void showUpdateMember() {
		System.out.print("Please enter the members ID (integers only): ");
		int id = getUserInputNumber();
		validateID(id);
		System.out.print("Please enter a new name: ");
		String name = getUserInputText();
		System.out.print("Please enter a new personal number (format yymmdd): ");
		int persNr = getUserInputNumber();
		
		currRegistry.updateMember(name, persNr, id);
		
		showMenu();
	}
	
	private void validateID(int id) {
		while (id < 0 || id > currRegistry.whatIsID()) {
			System.out.print("Invalid input. Please try again: ");
			id = getUserInputNumber();
		}
	}
	
	private void showSearchMember() {
		System.out.print("Please enter the members ID (integers only): ");
		int id = getUserInputNumber();
		validateID(id);
		searchForMember(id);
		showMenu();
	}
	
	//Finds the member with a specific ID and print the vorbose list for that user.
	private void searchForMember(int id) {
		for(Member member : currRegistry.getMemberList()) {
			if (member.getID() == id) {
				System.out.println("Name: " + member.getName() + "\nPersonal Number: " + member.getPersonalNumber() + "\nMember ID: " + member.getID() + "\n");
				
				
				int i = 0;
				for(Boat boat : member.getBoats()) {
					System.out.println(i + "Boat Type: " + boat.typeOfBoatString() + "\nLength: " + boat.getLength() + " cm");
					i++;
				}
			}
		}
	}
	
	private void showRegisterBoat() {
		System.out.println("Available boat types are: Sailboat, Motorsailer, KayakOrCanoe ad Other");
		System.out.print("Enter a boat type: ");
		String boat = getUserInputText();
		while (boat != "Sailboat" || boat != "Motorsailer" || boat != "KayakOrCanoe" || boat != "Other") {
			System.out.print("Invalid input. Please try again: ");
			boat = getUserInputText();
		}
			
		System.out.print("Length of boat (in cm): ");
		int length = getUserInputNumber();
		System.out.print("Enter the member ID to which the boat is to be registered (integers only): ");
		int id = getUserInputNumber();
		validateID(id);
		currRegistry.registerBoat(boat, length, id);
		
		showMenu();
	}
	
	private void showRemoveBoat() {
		System.out.print("Please enter the members ID (integers only): ");
		int id = getUserInputNumber();
		validateID(id);
		searchForMember(id);
		
		System.out.print("Which boat would you like to remove (enter the number representing the boat): ");
		int boatID = getUserInputNumber();
		currRegistry.removeBoat(id, boatID);
		
		showMenu();
	}
	
	private void showChangeBoat() {
		//Searches for the user which has the boat we want to change.
		System.out.print("Please enter the members ID (integers only): ");
		int id = getUserInputNumber();
		validateID(id);
		searchForMember(id);
		
		System.out.print("Which boat would you like to change (enter the number representing the boat): ");
		int boatID = getUserInputNumber();
		System.out.print("Enter a new boat type: ");
		String boat = getUserInputText();
		System.out.print("New length of boat (in cm): ");
		int length = getUserInputNumber();
		
		currRegistry.changeBoat(id, boatID, boat, length);
		
		showMenu();
	}
}

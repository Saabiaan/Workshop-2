package workshop2.model;

import java.util.*;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MemberRegistry {
	//List of members.
	private List<Member> memberList = new ArrayList<Member>();
	
	//The unique ID of the members.
	private int id = 0;
	
	//Registry file where member data is read and stored.
	File file = new File("/Users/Sebastian/Documents/test.txt");
	
	
	//Constructor which reads the member registry saved in the registry file.
	public MemberRegistry() { readFromFile(file); }
	
	//Returns the list of the members
	public List<Member> getMemberList() {	return memberList;	}
	
	//Method to generate each members unique ID.
	private int generateID() { return id++; }
	
	public int whatIsID() { return id; }
	
	
	public void addMember(String memberName, int memberPersNr) {
		//Set the int id so that it is a unique one and ready to be assigned to member.
		generateID();
		Member currMember = new Member(memberName, memberPersNr, id);
		
		//Add member to list.
		memberList.add(currMember);
		//Update file.
		writeToFile(file);
	}
	
	public void removeMember(int id) {
		//Find user with the specified id and remove it from the list.
		for (Member member : memberList) {
			if (member.getID() == id) {
				memberList.remove(member);
				break;
			}
		}
		//Update file.
		writeToFile(file);
	}

	public void updateMember(String name, int persNr, int id) {
		for (Member member : memberList) {
			if (member.getID() == id) {
				member.setName(name);
				member.setPersonalNumber(persNr);
				break;
			}
		}
		//Update file.
		writeToFile(file);
	}

	public void registerBoat(String boatType, int length, int id) {
		//Create new boat and set the properties of the boat and then send it to addBoatToMember
		//so it can be added to a members boat list.
		Boat boat = new Boat();
		switch (boatType) {
		case("Sailboat"):
			boat.setBoatType(Boat.TypeOfBoat.Sailboat);
			boat.setLength(length);
			addBoatToMember(boat, id);
			break;
			
		case("Motorsailer"):
			boat.setBoatType(Boat.TypeOfBoat.Motorsailer);
			boat.setLength(length);
			addBoatToMember(boat, id);
			break;

		case("KayakOrCanoe"):
			boat.setBoatType(Boat.TypeOfBoat.KayakOrCanoe);
			boat.setLength(length);
			addBoatToMember(boat, id);
			break;
			
		case("Other"):
			boat.setBoatType(Boat.TypeOfBoat.Other);
			boat.setLength(length);
			addBoatToMember(boat, id);
			break;
		}
	}

	private void addBoatToMember(Boat boat, int id) {
		for (Member member : memberList) {
			if (member.getID() == id) {
				member.setBoat(boat);
				break;
			}
		}
		//Update file.
		writeToFile(file);
	}
	
	public void removeBoat(int id, int boatID) {
		for (Member member : memberList) {
			if (member.getID() == id) {
				for (Boat boat : member.getBoats()) {
					if (boat.getBoatID() == boatID) {
						member.removeBoat(boat);
						break;
					}
				}
				
			}
		}
		//Update file.
		writeToFile(file);
	}

	public void changeBoat(int id, int boatID, String boatType, int length) {
		for (Member member : memberList) {
			if (member.getID() == id) {
				for (Boat boat : member.getBoats()) {
					if (boat.getBoatID() == boatID) {
						boat.setBoatType(Boat.TypeOfBoat.valueOf(boatType));
						boat.setLength(length);
						break;
					}
				}
				
			}
		}
		//Update file.
		writeToFile(file);
	}
	public void readFromFile(File file) {
		try (Scanner in = new Scanner(file)) {
			//While there are more lines in file.
			while (in.hasNext()) {
				//Get name of member.
				String line = in.nextLine();
				String name = line;
				
				//Get personal number of member.
				int number = in.nextInt();
				int personalNumber = number;
				
				//Get ID of member.
				number = in.nextInt();
				int idFromFile = number;
				
				//Create new member with the name, personal number and ID.
				Member tempMember = new Member(name, personalNumber, idFromFile);
				
				//While there are more boats.
				while ((line = in.nextLine()) == "Boat type:") {
					String boatType = in.nextLine();
					int boatLength = in.nextInt();
					in.nextLine();
					
					//Create new boat and assign it the values for type and length.
					Boat tempBoat = new Boat();
					tempBoat.setBoatType(Boat.TypeOfBoat.valueOf(boatType));
					tempBoat.setLength(boatLength);
					tempMember.setBoat(tempBoat);
				}
				//Add member to list.
				memberList.add(tempMember);
				//Update ID so that it matches the file.
				id++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void writeToFile(File file) {
		//Empty the file to make sure there are no duplicates..
		try (PrintWriter emptyWriter = new PrintWriter(file)) {
			emptyWriter.close();
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Write the memberList to file.
		try (PrintWriter pw = new PrintWriter(file)) {
			for (Member m : memberList) {
				pw.println(m.getName());
				pw.println(m.getPersonalNumber());
				pw.println(m.getID());
				pw.println("");
				 
				for (Boat b : m.getBoats()) {
				pw.println("Boat type:");
				pw.println(b.typeOfBoatString());
				pw.println("Length:");
				pw.println(b.getLength());
				pw.println("");
				}
			}
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}

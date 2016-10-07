package workshop2.model;

import java.util.*;

public class Member {
	//Members name, personal number and unique ID.
	private String name;
	private int personalNumber;
	private int id;
	
	//List of members boats.
	private List<Boat> boatList = new ArrayList<Boat>();
	
	//Constructor which makes a member with name, personal number and unique ID.
	public Member(String memberName, int persNr, int id) {
		name = memberName;
		personalNumber = persNr;
		this.id = id;
	}
	//Returns the name of the member.
	public String getName() { return name;	}
	
	//Returns the personal number of the member.
	public int getPersonalNumber() { return personalNumber;	}
	
	//Returns the unique ID of the member.
	public int getID() { return id;	}
	
	//Returns the list of the members boat/boats.
	public List<Boat> getBoats() { return boatList;	}
	
	//Sets the name of the member.
	public void setName(String memberName) { name = memberName; }
	
	//Sets the personal number of the member.
	public void setPersonalNumber(int persNr) { personalNumber = persNr; }
	
	//public void setID(int memberID) { id = memberID;	}
	
	//Adds a boat to the members boat list.
	public void setBoat(Boat boatType) { boatList.add(boatType);	}
	
	//Removes a boat from the members boat list.
	public void removeBoat(Boat boat) { boatList.remove(boat); }
}

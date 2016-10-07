package workshop2.model;

public class Boat {
	private TypeOfBoat boatType;
	private int lengthOfBoat;
	private int boatID;
	
	//Enum with the different boat types.
	public enum TypeOfBoat {
		Sailboat,
		Motorsailer,
		KayakOrCanoe,
		Other
	}
	//Returns the length of the boat. 
	public int getLength() { return lengthOfBoat; }
	
	//Sets the length of the boat.
	public void setLength(int length) { lengthOfBoat = length; }
	
	//Returns the index number of the users boat.
	public int getBoatID() { return boatID; }
	
	//Sets the user boat index number.
	public void setBoatID(int boatNum) { boatID = boatNum; }
	
	//Sets the type of boat that the user has.
	public void setBoatType(TypeOfBoat typeBoat) { boatType = typeBoat;	}
	
	//Returns type of boat as string.
	public String typeOfBoatString() { return boatType + "";}
}

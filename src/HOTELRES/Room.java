package HOTELRES;

public class Room {
	
	
	private String RoomType ;
	private int dailyCost;
	private int roomSize ;
	private Boolean hasBath;
	
    public Room(String type, int dailyCost, int roomSize, boolean hasBath) {
        this.RoomType = type;
        this.dailyCost = dailyCost;
        this.roomSize = roomSize;
        this.hasBath = hasBath;
    }

	public String getRoomType() {
		return RoomType;
	}

	public void setRoomType(String roomType) {
		RoomType = roomType;
	}

	public int getDailyCost() {
		return dailyCost;
	}

	public void setDailyCost(int dailyCost) {
		this.dailyCost = dailyCost;
	}

	public int getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}

	public Boolean getHasBath() {
		return hasBath;
	}

	public void setHasBath(Boolean hasBath) {
		this.hasBath = hasBath;
	}


}

class Single extends Room {
    public Single() {
        super("Single", 100, 15, false);
    }
}

class Club extends Room {
    public Club() {
        super("Club", 250, 45, true);
    }
}

class Double extends Room {
    public Double() {
        super("Double", 180, 30, false);
    }
}

class Family extends Room {
    public Family() {
        super("Family", 400, 50, false);
    }
}

class FamilyView extends Room{
	public FamilyView() {
		super("Family with View",450,50,true);
	}
}

class Suite extends Room{
	public Suite() {
		super("Suite",450,50,true);
	}
}


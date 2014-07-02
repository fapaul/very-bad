package Datatypes.Added;

import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.environment.Position;

public class RoomPoint {

	protected Position location;
	protected StockroomID room;
	
	Cart interact(Cart cart) {
		return cart;
	}

	public Position getLocation() {
		return location;
	}

	public StockroomID getRoom() {
		return room;
	}

	public void setLocation(Position location) {
		this.location = location;
	}

	public void setRoom(StockroomID room) {
		this.room = room;
	}
}
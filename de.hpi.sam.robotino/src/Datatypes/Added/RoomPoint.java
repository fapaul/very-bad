package Datatypes.Added;

import de.hpi.sam.warehouse.WarehouseManagement;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;
import de.cpslab.robotino.environment.Position;

public class RoomPoint {

	protected Position location;
	protected StockroomID room;
	
	public RoomPoint(Position location) {
		WarehouseRepresentation warehouseRep = new WarehouseRepresentation();
		this.location = location;
		this.room = warehouseRep.getRoomFor(location);
		System.out.println("testet " + (this.room==null) + "\t" + location.getXPosition());
	}
	
	public RoomPoint(Position location, StockroomID room) {
		this.location = location;
		this.room = room;
	}
	
	
	public RoomPoint(int positionX, int positionZ) {
		WarehouseRepresentation warehouseRep = new WarehouseRepresentation();
		this.location = new Position(positionX, positionZ);
		this.room = warehouseRep.getRoomFor(location);
	}	
	
	Cart interact(Cart cart) {
		return interact(cart, null);
	}

	
	Cart interact(Cart cart, WarehouseRobot robot) {
		return cart;
	}

	public Position getLocation() {
		return location;
	}

	public StockroomID getRoom() {
		return this.room;
	}

	public void setLocation(Position location) {
		this.location = location;
	}

	public void setRoom(StockroomID room) {
		this.room = room;
	}
	
	public double distance(RoomPoint other) {
		return Math.sqrt(Math.pow(other.location.getXPosition() - this.location.getXPosition(), 2)
				+ (Math.pow(other.location.getZPosition() - this.location.getZPosition(), 2)));
	}
}
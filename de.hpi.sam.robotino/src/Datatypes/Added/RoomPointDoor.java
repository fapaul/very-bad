package Datatypes.Added;

import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class RoomPointDoor extends RoomPoint {
	private WarehouseRepresentation rep = new WarehouseRepresentation();
	
	private Door door;
	
	public RoomPointDoor(Door door) {
		super(door.getPosition());
		this.door = door;
	}
	
	public Door getDoor() {
		return door;
	}
}
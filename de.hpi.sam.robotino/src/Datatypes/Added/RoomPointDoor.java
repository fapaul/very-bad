package Datatypes.Added;

import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class RoomPointDoor extends RoomPoint {
	private WarehouseRepresentation rep;
	
	public RoomPointDoor(Door door) {
		super();
		this.location = door.getPosition();
		this.room = rep.getRoomFor(door.getPosition());
	}	
}
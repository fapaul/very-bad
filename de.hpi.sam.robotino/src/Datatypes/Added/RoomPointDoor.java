package Datatypes.Added;

import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;

public class RoomPointDoor extends RoomPoint {
	private WarehouseRepresentation rep = new WarehouseRepresentation();
	
	public RoomPointDoor(Door door) {
		super(door.getPosition());
	}	
}
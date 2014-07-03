package Datatypes.Added;

import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.Cart;

public class RoomPointChargingStation extends RoomPoint {
	
	public RoomPointChargingStation(WarehouseRobot robot) {
		super(robot.getCurrentPosition());
	}
	
	@Override
	public Cart interact(Cart cart,  WarehouseRobot robot){
		
		return null;
	}
}

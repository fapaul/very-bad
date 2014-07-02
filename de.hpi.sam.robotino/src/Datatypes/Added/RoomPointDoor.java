package Datatypes.Added;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.stock.Cart;
import de.cpslab.robotino.*;

public class RoomPointDoor extends RoomPoint {
	@Override
	Cart interact(Cart cart, WarehouseRobot robot) {
		return cart;
	}

}
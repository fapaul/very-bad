package Datatypes.Added;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.cpslab.robotino.*;

public class RoomPointIssuingPoint extends RoomPoint {

	private int itemsToTake;
	private IssuingPoint point;

	public void setItemsToTake(int itemsToTake) {
		this.itemsToTake = itemsToTake;
	}

	public int getItemsToTake() {
		return itemsToTake;
	}
	
	@Override
	Cart interact(Cart cart, WarehouseRobot robot) {
		return cart;
	}

}
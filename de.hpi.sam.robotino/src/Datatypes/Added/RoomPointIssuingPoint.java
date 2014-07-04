package Datatypes.Added;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;

public class RoomPointIssuingPoint extends RoomPoint {
	
	private int itemsToTake;
	private IssuingPoint point;

	public RoomPointIssuingPoint(Position location, IssuingPoint point) {
		super(location);
		this.point = point;
	}
	
	public RoomPointIssuingPoint(IssuingPoint point) {
		super(point);
		this.point = point;
	}
	
	public void setItemsToTake(int itemsToTake) {
		this.itemsToTake = itemsToTake;
	}

	public int getItemsToTake() {
		return itemsToTake;
	}
	
	public IssuingPoint getIssuingPoint() {
		return point;
	}
	
	@Override
	public Cart interact(Cart cart, WarehouseRobot robot) {
		robot.load(itemsToTake, point, cart);
		return cart;
	}

}
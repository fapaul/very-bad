package Datatypes.Added;

import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.WarehouseRobot;


public class CartDestination extends RoomPointCartArea {

	WarehouseRobot warehouseRobot = new WarehouseRobot(new RobotinoID("000.000.000.000"));

	public CartDestination(CartArea cartArea) {
		super(cartArea);
		carts = cartArea.getCartPositions();
		this.location = (Position)carts.get(0);
		this.room = warehouseRobot.getRoomFor(location);
	}
	
	public Cart interact(Cart cart){
		if (carts.size() < 1)
			return null;
		warehouseRobot.returnCart(cart, carts.get(0));
		carts.remove(0);
		return null;
	}
}

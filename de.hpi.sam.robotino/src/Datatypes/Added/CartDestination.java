package Datatypes.Added;

import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;


public class CartDestination extends RoomPointCartArea {

	WarehouseRobot warehouseRobot;

	public CartDestination(CartArea cartArea) {
		super(cartArea);
		WarehouseRepresentation rep = new WarehouseRepresentation();
		carts = cartArea.getCartPositions();
		
		this.location = (Position)carts.get(0);
		this.room = rep.getRoomFor(location);
	}
	
	@Override
	public Cart interact(Cart cart,  WarehouseRobot robot){
		this.warehouseRobot = robot;
		if (carts.size() < 1)
			return null;
		warehouseRobot.returnCart(cart, carts.get(0));
		carts.remove(0);
		return null;
	}
}

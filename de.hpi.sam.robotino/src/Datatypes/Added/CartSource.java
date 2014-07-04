package Datatypes.Added;


import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.hpi.sam.warehouse.stock.WarehouseRepresentation;


public class CartSource extends RoomPointCartArea {
	//WarehouseRobot warehouseRobot = new WarehouseRobot(new RobotinoID("000.000.000.000"));
	private WarehouseRobot warehouseRobot;
	public CartSource(CartArea cartArea) {
		super(cartArea);
		carts = cartArea.getCartPositions();
		this.location = (Position)carts.get(0);
		WarehouseRepresentation rep = new WarehouseRepresentation();
		this.room = rep.getRoomFor(location);
		 
	}
	
	@Override
	public Cart interact(Cart cart,  WarehouseRobot robot){
		this.warehouseRobot = robot;
		if (carts.size() < 1)
			return null;
		CartPosition cartPosition = carts.get(0);
		carts.remove(0);
		return warehouseRobot.takeCart(cartPosition);
	}

}
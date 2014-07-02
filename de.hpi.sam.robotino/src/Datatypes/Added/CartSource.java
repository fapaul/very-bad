package Datatypes.Added;


import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.CartPosition;




public class CartSource extends RoomPointCartArea {
	WarehouseRobot warehouseRobot = new WarehouseRobot(new RobotinoID("000.000.000.000"));
	
	public CartSource(CartArea cartArea) {
		super(cartArea);
		carts = cartArea.getCartPositions();
		this.location = (Position)carts.get(0);
		this.room = warehouseRobot.getRoomFor(location);
	}
	
	public Cart interact(Cart cart){
		if (carts.size() < 1)
			return null;
		CartPosition cartPosition = carts.get(0);
		carts.remove(0);
		return warehouseRobot.takeCart(cartPosition);
	}

}
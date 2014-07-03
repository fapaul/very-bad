package Datatypes.Added;


import de.cpslab.robotino.RobotinoID;
import de.cpslab.robotino.environment.Position;
import de.hpi.sam.warehouse.WarehouseRobot;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;




public class CartSource extends RoomPointCartArea {
	private WarehouseRobot warehouseRobot;
	
	public CartSource(CartArea cartArea, StockroomID stock) {
		super(cartArea);
		carts = cartArea.getCartPositions();
		this.location = (Position)carts.get(0);
		//this.room = warehouseRobot.getRoomFor(location);
		this.room = room; 
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
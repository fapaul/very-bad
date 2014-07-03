package Datatypes.Added;

import java.util.List;

import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.order.ProductType;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;
import RobotSystem.Implementation.*;
import de.hpi.sam.warehouse.environment.Path;
import de.hpi.sam.warehouse.interfaces.*;

public class RoomPointDoor extends RoomPoint implements IStockroom {
	//private RoomPointDoor rpDoor;
	
	public RoomPointDoor(Door door) {
		super();
		this.location = door.getPosition();
		this.room = getRoomFor(door.getPosition());
	}

	@Override
	public boolean load(int numberOfProducts, IssuingPoint issuingPoint,
			Cart cart) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cart takeCart(CartPosition cartPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean returnCart(Cart cart, CartPosition cartPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<StockroomID> getStockrooms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IssuingPoint> getIssuingPoints(ProductType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IssuingPoint> getAllIssueingPoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartArea> getCartAreas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Path> computePaths(StockroomID roomSrc, StockroomID roomDst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockroomID getRoomFor(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Door> getDoors(StockroomID stockroom) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
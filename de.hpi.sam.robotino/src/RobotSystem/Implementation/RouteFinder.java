package RobotSystem.Implementation;

import java.util.List;

import Datatypes.Added.RoomPoint;
import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRouteFinder;
import de.hpi.sam.warehouse.*;
import de.hpi.sam.warehouse.environment.Path;
import de.hpi.sam.warehouse.interfaces.IStockroom;
import de.hpi.sam.warehouse.order.Order;
import de.hpi.sam.warehouse.order.ProductType;
import de.hpi.sam.warehouse.stock.Cart;
import de.hpi.sam.warehouse.stock.CartArea;
import de.hpi.sam.warehouse.stock.CartPosition;
import de.hpi.sam.warehouse.stock.Door;
import de.hpi.sam.warehouse.stock.IssuingPoint;
import de.hpi.sam.warehouse.stock.Stockroom;
import de.hpi.sam.warehouse.stock.StockroomID;
import de.cpslab.robotino.*;
import de.cpslab.robotino.environment.Position;
import de.cpslab.robotino.sensor.interfaces.INorthStar;

public class RouteFinder implements INorthStar, IStockroom, IRouteFinder {
	
	private Position CHARGINGSTATION;
	private Position destinationPosition;
	
	private Route[] calculateSubRoute(RoomPoint from, RoomPoint to) {
	
		return null;
	}
	
	private Position chooseCartArea() {
	
		return null;
	}

	@Override
	public Route[] calculateCartAreaRoutes(Position from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route[] calculateCartAreaRoutes(Position from, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route[] calculateIssuingPointsRoutes(Position from, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route calculateExplorationRoute(Position from, Stockroom room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Route calculateChargingRoute(Position from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDistance(Route route) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StockroomID[] getNearRooms() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public Position getCurrentPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCurrentOrientation() {
		// TODO Auto-generated method stub
		return 0;
	}
}
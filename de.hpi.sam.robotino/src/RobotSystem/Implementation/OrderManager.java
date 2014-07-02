package RobotSystem.Implementation;

import java.util.Date;
import java.util.List;

import Datatypes.Added.Route;
import RobotSystem.Interfaces.New.IRobotExecute;
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

public class OrderManager implements IStockroom, IRobotExecute, IRouteFinder {
// DriverManager, ExplorationManager

	private boolean done;
	private Route currentSourceRoute;
	private Route currentDestinationRoute;
	private Order currentOrder;
	private Cart currentCart;
	
	private Route chooseOrderRoute(Route[] routes, Order order) {
	
		return null;
	}
	
	private Route chooseRoute(Route[] routes) {
	
		return null;
	}

	@Override
	public List<Route> calculateCartAreaRoutes(Position from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Route> calculateCartAreaRoutes(Position from, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Route> calculateIssuingPointsRoutes(Position from, Order order) {
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
	public void orderStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean orderDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date calculateOrderTime(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void explorationStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean explorationDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasUnexploredRooms() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void chargingStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean chargingDone() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBumped() {
		// TODO Auto-generated method stub
		return false;
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